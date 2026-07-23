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
            
            <div style={{ display: 'grid', gap: '2rem', gridTemplateColumns: 'repeat(auto-fit, minmax(350px, 1fr))' }}>
              <div style={{ textAlign: 'center' }}>
                <img src="/images/statutory/guide1.png" alt="신고 가이드 1" style={{ width: '100%', borderRadius: '8px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }} />
              </div>
              <div style={{ textAlign: 'center' }}>
                <img src="/images/statutory/guide2.png" alt="신고 가이드 2" style={{ width: '100%', borderRadius: '8px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }} />
              </div>
              <div style={{ textAlign: 'center' }}>
                <img src="/images/statutory/guide3.png" alt="신고 가이드 3" style={{ width: '100%', borderRadius: '8px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }} />
              </div>
              <div style={{ textAlign: 'center' }}>
                <img src="/images/statutory/guide4.png" alt="신고 가이드 4" style={{ width: '100%', borderRadius: '8px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }} />
              </div>
              <div style={{ textAlign: 'center' }}>
                <img src="/images/statutory/guide5.png" alt="신고 가이드 5" style={{ width: '100%', borderRadius: '8px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)' }} />
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
