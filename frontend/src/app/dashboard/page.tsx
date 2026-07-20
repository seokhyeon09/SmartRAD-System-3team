import type { Metadata } from "next";

import DashboardPage from "@/component/dashboard/DashboardPage/DashboardPage";

export const metadata: Metadata = {
  title: "대시보드 | SmartRAD HR",
  description: "SmartRAD HR 병원 인사관리 대시보드",
};

export default function DashboardRoutePage() {
  return <DashboardPage />;
}
