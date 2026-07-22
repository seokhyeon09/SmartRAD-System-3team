package com.tphr.hr.attendance.repository;

import com.tphr.hr.attendance.entity.DutyScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyScheduleEntryRepository extends JpaRepository<DutyScheduleEntry, Long> {
    List<DutyScheduleEntry> findByDutyScheduleId(Long dutyScheduleId);
    void deleteByDutyScheduleId(Long dutyScheduleId);
}
