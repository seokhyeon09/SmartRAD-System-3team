export interface EmployeeSummaryResponse {
  id: number;
  empNo: string;
  name: string;
  departmentName: string;
  positionName?: string;
  roleGroupName: string;
  accountStatus: string; // "ACTIVE" | "LOCKED"
}

export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export interface EmployeeUpdateRequest {
  roleGroupId?: number;
  // Other fields can be added here if needed for partial update
}

export interface AccountStatusUpdateRequest {
  accountStatus: "ACTIVE" | "LOCKED";
}

export interface EmployeeCreateRequest {
  empNo: string;
  name: string;
  email?: string;
  joinDate: string; // "YYYY-MM-DD"
  departmentId?: number;
  roleGroupId?: number;
}
