package com.tphr.hr.approval.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ApprovalResponse {
    private Long id;
    private String docNumber;
    private String title;
    private String docTypeName;
    private String draftedByName;
    private String status; // DRAFT, IN_PROGRESS, COMPLETED, REJECTED
    private LocalDateTime createdAt;
}
