package com.tphr.hr.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DutyScheduleEntryRequest {
    private Long employeeId;
    private LocalDate workDate;
    private String shiftTypeCode; // e.g., "SHIFT_D", "SHIFT_E", "SHIFT_N", "SHIFT_OFF"
}
