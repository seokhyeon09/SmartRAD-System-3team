package com.tphr.hr.payroll.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class PayrollRecordWithDetailsResponse {
    private PayrollResponse record;
    private List<PayrollDetailResponse> details;
}
