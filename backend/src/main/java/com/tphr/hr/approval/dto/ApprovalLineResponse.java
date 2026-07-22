package com.tphr.hr.approval.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ApprovalLineResponse {
    private Long id;
    private Integer sequence;
    private String approverName;
    private String status; // WAITING, APPROVED, REJECTED
    private LocalDateTime approvedAt;
    private String rejectReason;
}
