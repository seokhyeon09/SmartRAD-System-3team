package com.tphr.hr.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalUpdateRequest {
    private String title;
    private String content; // JSON string or HTML text
}
