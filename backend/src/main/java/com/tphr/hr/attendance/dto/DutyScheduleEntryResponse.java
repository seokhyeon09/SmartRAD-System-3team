package com.tphr.hr.attendance.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class DutyScheduleEntryResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate workDate;
    private String shiftTypeCode;
    private String shiftTypeName;
}
