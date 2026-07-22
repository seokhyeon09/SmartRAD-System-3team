package com.tphr.hr.attendance.repository;

import com.tphr.hr.attendance.entity.DutySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DutyScheduleRepository extends JpaRepository<DutySchedule, Long> {
    Optional<DutySchedule> findByDepartmentIdAndScheduleYearAndScheduleMonth(Long departmentId, Integer scheduleYear, Integer scheduleMonth);
}
