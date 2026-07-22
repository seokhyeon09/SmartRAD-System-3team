package com.tphr.hr.payroll.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class PayrollDetailResponse {
    private Long id;
    private String itemType; // ALLOWANCE or DEDUCTION
    private String itemName;
    private BigDecimal amount;
}
