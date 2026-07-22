package com.tphr.hr.attendance.service;

import com.tphr.hr.attendance.dto.DutyScheduleCreateRequest;
import com.tphr.hr.attendance.dto.DutyScheduleEntryRequest;
import com.tphr.hr.attendance.dto.DutyScheduleEntryResponse;
import com.tphr.hr.attendance.dto.DutyScheduleResponse;
import com.tphr.hr.attendance.entity.DutySchedule;
import com.tphr.hr.attendance.entity.DutyScheduleEntry;
import com.tphr.hr.attendance.repository.DutyScheduleEntryRepository;
import com.tphr.hr.attendance.repository.DutyScheduleRepository;
import com.tphr.hr.employee.entity.Department;
import com.tphr.hr.employee.entity.Employee;
import com.tphr.hr.employee.repository.DepartmentRepository;
import com.tphr.hr.employee.repository.EmployeeRepository;
import com.tphr.hr.system.entity.CommonCode;
import com.tphr.hr.system.entity.Menu;
import com.tphr.hr.system.entity.RolePermission;
import com.tphr.hr.system.repository.CommonCodeRepository;
import com.tphr.hr.system.repository.MenuRepository;
import com.tphr.hr.system.repository.RolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DutyScheduleService {

    private final DutyScheduleRepository dutyScheduleRepository;
    private final DutyScheduleEntryRepository dutyScheduleEntryRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CommonCodeRepository commonCodeRepository;
    
    // 권한 검증용
    private final MenuRepository menuRepository;
    private final RolePermissionRepository rolePermissionRepository;

    private void validateWritePermission(Long requesterId) {
        Employee requester = employeeRepository.findById(requesterId)
                .orElseThrow(() -> new IllegalArgumentException("요청자를 찾을 수 없습니다."));

        if (requester.getRoleGroup() == null) {
            throw new IllegalStateException("권한 그룹이 설정되지 않은 사용자입니다.");
        }

        Menu dutyMenu = menuRepository.findByMenuCode("DUTY_SCHEDULE")
                .orElseThrow(() -> new IllegalStateException("시스템 오류: DUTY_SCHEDULE 메뉴가 존재하지 않습니다."));

        RolePermission permission = rolePermissionRepository.findByRoleGroupIdAndMenuId(requester.getRoleGroup().getId(), dutyMenu.getId())
                .orElseThrow(() -> new IllegalStateException("해당 메뉴에 대한 권한 설정이 없습니다. (403 Forbidden)"));

        if (!permission.getCanWrite()) {
            throw new IllegalStateException("듀티표 작성/수정 권한이 없습니다. (403 Forbidden)");
        }
    }

    @Transactional
    public DutyScheduleResponse createDutySchedule(DutyScheduleCreateRequest request) {
        validateWritePermission(request.getRequesterId());

        if (dutyScheduleRepository.findByDepartmentIdAndScheduleYearAndScheduleMonth(
                request.getDepartmentId(), request.getScheduleYear(), request.getScheduleMonth()).isPresent()) {
            throw new IllegalStateException("해당 월의 듀티표가 이미 존재합니다.");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        DutySchedule schedule = DutySchedule.builder()
                .department(department)
                .scheduleYear(request.getScheduleYear())
                .scheduleMonth(request.getScheduleMonth())
                .status("DRAFT")
                .build();

        DutySchedule saved = dutyScheduleRepository.save(schedule);
        return mapToResponse(saved, List.of());
    }

    @Transactional
    public DutyScheduleResponse assignEntries(Long scheduleId, List<DutyScheduleEntryRequest> requests, Long requesterId) {
        validateWritePermission(requesterId);

        DutySchedule schedule = dutyScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("듀티표를 찾을 수 없습니다."));

        // 기존 항목 모두 삭제 후 새로 삽입 (단순화된 bulk-replace 로직)
        dutyScheduleEntryRepository.deleteByDutyScheduleId(scheduleId);

        List<DutyScheduleEntry> newEntries = requests.stream().map(req -> {
            Employee emp = employeeRepository.findById(req.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("직원을 찾을 수 없습니다."));
            CommonCode shiftCode = commonCodeRepository.findById(req.getShiftTypeCode())
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 근무 형태입니다: " + req.getShiftTypeCode()));

            return DutyScheduleEntry.builder()
                    .dutySchedule(schedule)
                    .employee(emp)
                    .workDate(req.getWorkDate())
                    .shiftType(shiftCode)
                    .build();
        }).collect(Collectors.toList());

        List<DutyScheduleEntry> savedEntries = dutyScheduleEntryRepository.saveAll(newEntries);
        return mapToResponse(schedule, savedEntries);
    }

    @Transactional
    public DutyScheduleResponse confirmDutySchedule(Long scheduleId, Long requesterId) {
        validateWritePermission(requesterId);

        DutySchedule schedule = dutyScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("듀티표를 찾을 수 없습니다."));

        DutySchedule confirmed = DutySchedule.builder()
                .id(schedule.getId())
                .department(schedule.getDepartment())
                .scheduleYear(schedule.getScheduleYear())
                .scheduleMonth(schedule.getScheduleMonth())
                .status("CONFIRMED")
                .build();

        DutySchedule saved = dutyScheduleRepository.save(confirmed);
        return mapToResponse(saved, dutyScheduleEntryRepository.findByDutyScheduleId(scheduleId));
    }

    @Transactional(readOnly = true)
    public DutyScheduleResponse getDutySchedule(Long departmentId, Integer year, Integer month) {
        DutySchedule schedule = dutyScheduleRepository.findByDepartmentIdAndScheduleYearAndScheduleMonth(departmentId, year, month)
                .orElseThrow(() -> new IllegalArgumentException("해당 부서의 특정 월 듀티표가 존재하지 않습니다."));
        
        List<DutyScheduleEntry> entries = dutyScheduleEntryRepository.findByDutyScheduleId(schedule.getId());
        return mapToResponse(schedule, entries);
    }

    private DutyScheduleResponse mapToResponse(DutySchedule schedule, List<DutyScheduleEntry> entries) {
        List<DutyScheduleEntryResponse> entryResponses = entries.stream().map(e -> DutyScheduleEntryResponse.builder()
                .id(e.getId())
                .employeeId(e.getEmployee().getId())
                .employeeName(e.getEmployee().getName())
                .workDate(e.getWorkDate())
                .shiftTypeCode(e.getShiftType().getCode())
                .shiftTypeName(e.getShiftType().getName())
                .build()).collect(Collectors.toList());

        return DutyScheduleResponse.builder()
                .id(schedule.getId())
                .departmentId(schedule.getDepartment().getId())
                .departmentName(schedule.getDepartment().getName())
                .scheduleYear(schedule.getScheduleYear())
                .scheduleMonth(schedule.getScheduleMonth())
                .status(schedule.getStatus())
                .entries(entryResponses)
                .build();
    }
}
