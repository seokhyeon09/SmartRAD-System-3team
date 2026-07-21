package com.tphr.hr.approval.repository;

import com.tphr.hr.approval.entity.ApprovalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalDocumentRepository extends JpaRepository<ApprovalDocument, Long> {
    
    // 내가 기안한 문서 목록 조회
    List<ApprovalDocument> findByDraftedByIdOrderByCreatedAtDesc(Long draftedById);
}
