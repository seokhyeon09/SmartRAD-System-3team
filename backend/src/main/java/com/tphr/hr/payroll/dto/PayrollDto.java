package com.tphr.hr.payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class PayrollDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ManualRequest {
        private Long employeeId; // 추가(POST) 시 필요, 수정(PUT) 시 무시됨
        private Integer year;
        private Integer month;
        private BigDecimal baseSalary;
        private BigDecimal totalAllowance;
        private BigDecimal totalDeduction;
        private BigDecimal netPay;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private Long employeeId;
        private String employeeName;
        private Integer payrollYear;
        private Integer payrollMonth;
        private BigDecimal baseSalary;
        private BigDecimal totalAllowance;
        private BigDecimal totalDeduction;
        private BigDecimal netPay;
        private String status;
    }

    @Getter
    @Builder
    public static class DetailResponse {
        private Long id;
        private String itemType; // ALLOWANCE or DEDUCTION
        private String itemName;
        private BigDecimal amount;
    }

    @Getter
    @Builder
    public static class RecordWithDetailsResponse {
        private Response record;
        private List<DetailResponse> details;
    }
}
