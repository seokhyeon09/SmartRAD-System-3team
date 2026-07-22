package com.tphr.hr.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCheckInRequest {
    private Long employeeId;
    private LocalDate workDate;
    private LocalTime checkInTime;
    private String note;
}
