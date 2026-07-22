package com.tphr.hr.approval.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApprovalAttachmentResponse {
    private Long id;
    private String fileName;
    private String filePath;
    private Integer fileSizeKb;
}
