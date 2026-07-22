package com.tphr.hr.attendance.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class DutyScheduleResponse {
    private Long id;
    private Long departmentId;
    private String departmentName;
    private Integer scheduleYear;
    private Integer scheduleMonth;
    private String status; // DRAFT, CONFIRMED
    private List<DutyScheduleEntryResponse> entries;
}
