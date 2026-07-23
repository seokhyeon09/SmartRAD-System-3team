"use client";

import { useState } from "react";
import styles from "./StatutoryPage.module.scss";
import StatutoryDashboardTab from "./StatutoryDashboardTab";
import StatutoryCalendarTab from "./StatutoryCalendarTab";
import StatutoryScheduleModal from "./StatutoryScheduleModal";

export default function StatutoryPage() {
  const [activeTab, setActiveTab] = useState<"dashboard" | "calendar" | "guide">("dashboard");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [refreshKey, setRefreshKey] = useState(0);

  const handleSuccess = () => {
    setIsModalOpen(false);
    setRefreshKey(prev => prev + 1); // Trigger re-render of child components to fetch latest data
  };

  return (
    <div className={styles.pageContainer}>
      <header className={styles.header}>
        <div className={styles.titleBox}>
          <div>
            <h1>법정 신고</h1>
            <p>4대보험 신고, 원천징수 납부, 연말정산 등 법정 의무 신고 업무를 통합 관리합니다.</p>
          </div>
        </div>
        
        <div className={styles.tabList}>
          <button 
            className={`${styles.tabButton} ${activeTab === 'dashboard' ? styles.active : ''}`}
            onClick={() => setActiveTab('dashboard')}
          >
            법정 신고 대시보드
          </button>
          <button 
            className={`${styles.tabButton} ${activeTab === 'calendar' ? styles.active : ''}`}
            onClick={() => setActiveTab('calendar')}
          >
            신고 일정 보기
          </button>
          <button 
            className={`${styles.tabButton} ${activeTab === 'guide' ? styles.active : ''}`}
            onClick={() => setActiveTab('guide')}
          >
            신고 가이드
          </button>

          <button className={styles.primaryAction} onClick={() => setIsModalOpen(true)}>
            + 일정 추가
          </button>
        </div>
      </header>

      <main key={refreshKey}>
        {activeTab === 'dashboard' && <StatutoryDashboardTab />}
        {activeTab === 'calendar' && <StatutoryCalendarTab />}
        {activeTab === 'guide' && (
          <div className={styles.guideContainer} style={{ background: 'white', padding: '2rem', borderRadius: '12px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)' }}>
            <h2 style={{ marginBottom: '1.5rem', color: '#1e293b', fontSize: '1.5rem', fontWeight: 'bold' }}>법정 신고 기본 가이드</h2>
            
            <div style={{ display: 'grid', gap: '1.5rem', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))' }}>
              
              <div style={{ padding: '1.5rem', border: '1px solid #e2e8f0', borderRadius: '8px' }}>
                <h3 style={{ color: '#0ea5e9', fontSize: '1.1rem', marginBottom: '0.75rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
                  <span style={{ fontSize: '1.5rem' }}>💰</span> 원천세 및 지방소득세
                </h3>
                <p style={{ margin: '0 0 0.5rem', fontWeight: '600' }}>신고 기한: 매월 10일</p>
                <p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem', lineHeight: '1.5' }}>
                  급여 지급일이 속하는 달의 다음 달 10일까지 관할 세무서에 원천징수이행상황신고서를 제출하고 세액을 납부해야 합니다.
                </p>
              </div>

              <div style={{ padding: '1.5rem', border: '1px solid #e2e8f0', borderRadius: '8px' }}>
                <h3 style={{ color: '#10b981', fontSize: '1.1rem', marginBottom: '0.75rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
                  <span style={{ fontSize: '1.5rem' }}>🏥</span> 4대보험 취득/상실
                </h3>
                <p style={{ margin: '0 0 0.5rem', fontWeight: '600' }}>신고 기한: 다음 달 15일</p>
                <p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem', lineHeight: '1.5' }}>
                  신규 입사자나 퇴사자 발생 시, 사유 발생일이 속한 달의 다음 달 15일까지 4대사회보험 정보연계센터를 통해 신고해야 합니다.
                </p>
              </div>

              <div style={{ padding: '1.5rem', border: '1px solid #e2e8f0', borderRadius: '8px' }}>
                <h3 style={{ color: '#f59e0b', fontSize: '1.1rem', marginBottom: '0.75rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
                  <span style={{ fontSize: '1.5rem' }}>📄</span> 간이지급명세서
                </h3>
                <p style={{ margin: '0 0 0.5rem', fontWeight: '600' }}>신고 기한: 매월 말일 또는 반기 말일</p>
                <p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem', lineHeight: '1.5' }}>
                  상용근로소득 간이지급명세서는 매 반기 마지막 달의 다음 달 말일(7.31, 1.31)까지 제출하며, 일용근로자는 매월 제출합니다.
                </p>
              </div>

              <div style={{ padding: '1.5rem', border: '1px solid #e2e8f0', borderRadius: '8px' }}>
                <h3 style={{ color: '#8b5cf6', fontSize: '1.1rem', marginBottom: '0.75rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
                  <span style={{ fontSize: '1.5rem' }}>📊</span> 연말정산 (지급명세서)
                </h3>
                <p style={{ margin: '0 0 0.5rem', fontWeight: '600' }}>신고 기한: 다음 연도 3월 10일</p>
                <p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem', lineHeight: '1.5' }}>
                  근로소득 연말정산을 완료하고 2월분 급여에 반영한 후, 3월 10일까지 근로소득 지급명세서를 제출해야 합니다.
                </p>
              </div>
            </div>
            
            <div style={{ marginTop: '2rem', padding: '1rem', backgroundColor: '#f8fafc', borderRadius: '8px', borderLeft: '4px solid #3b82f6' }}>
              <p style={{ margin: 0, color: '#334155', fontSize: '0.9rem' }}>
                <strong>💡 참고 사항:</strong> 상기 일정은 일반적인 기준이며, 휴일이나 국세청 정책 변경에 따라 실제 기한이 연장될 수 있습니다. 
                정확한 일정은 항상 <strong>[신고 일정 보기]</strong> 탭의 캘린더를 참조해 주세요.
              </p>
            </div>
          </div>
        )}
      </main>

      {isModalOpen && (
        <StatutoryScheduleModal 
          onClose={() => setIsModalOpen(false)} 
          onSuccess={handleSuccess} 
        />
      )}
    </div>
  );
}
