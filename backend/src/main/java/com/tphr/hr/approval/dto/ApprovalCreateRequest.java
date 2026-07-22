package com.tphr.hr.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalCreateRequest {
    private String title;
    private String docTypeCode; // e.g., "DOC_VACATION", "DOC_WELFARE"
    private String content; // JSON string or HTML text
    private Long draftedById;
    private List<Long> approverIds; // 순차적 결재선 사번 목록
    private List<String> attachmentFileNames; // 임시 메타데이터용 파일명 리스트
}
