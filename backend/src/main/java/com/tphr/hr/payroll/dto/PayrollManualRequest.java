package com.tphr.hr.payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollManualRequest {
    private Long employeeId; // 추가(POST) 시 필요, 수정(PUT) 시 무시됨
    private Integer year;
    private Integer month;
    private BigDecimal baseSalary;
    private BigDecimal totalAllowance;
    private BigDecimal totalDeduction;
    private BigDecimal netPay;
}
