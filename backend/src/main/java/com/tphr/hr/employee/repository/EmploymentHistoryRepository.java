package com.tphr.hr.employee.repository;

import com.tphr.hr.employee.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {

    List<EmploymentHistory> findByEmployeeIdOrderByStartDateDesc(Long employeeId);

    @org.springframework.data.jpa.repository.Query("SELECT h FROM EmploymentHistory h WHERE h.employee.id IN :employeeIds " +
            "AND h.type.code IN ('STS_LEAVE', 'STS_RETIRE') " +
            "AND h.startDate <= :monthEnd AND (h.endDate IS NULL OR h.endDate >= :monthStart)")
    List<EmploymentHistory> findOverlappingLeavesForEmployees(
            @org.springframework.data.repository.query.Param("employeeIds") List<Long> employeeIds,
            @org.springframework.data.repository.query.Param("monthStart") java.time.LocalDate monthStart,
            @org.springframework.data.repository.query.Param("monthEnd") java.time.LocalDate monthEnd);
}
