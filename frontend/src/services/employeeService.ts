import type {
  EmployeeSummaryResponse,
  Page,
  EmployeeUpdateRequest,
  AccountStatusUpdateRequest,
  EmployeeCreateRequest,
} from "@/types/employee";

export interface AccountIssueResponse {
  empNo: string;
  temporaryPassword?: string; // Optional depending on how the backend sends it
}

const backendApiUrl = "/api-system";

function getHeaders() {
  const headers: HeadersInit = {
    "Content-Type": "application/json",
    Accept: "application/json",
  };
  
  if (typeof window !== "undefined") {
    const token = localStorage.getItem("accessToken");
    if (token) {
      headers["Authorization"] = `Bearer ${token}`;
    }
  }
  return headers;
}

export async function getEmployees(
  size = 50,
  keyword?: string,
  departmentId?: string,
  roleGroupId?: string
): Promise<Page<EmployeeSummaryResponse>> {
  let url = `${backendApiUrl}/employees?size=${size}`;
  if (keyword) url += `&keyword=${encodeURIComponent(keyword)}`;
  if (departmentId) url += `&departmentId=${encodeURIComponent(departmentId)}`;
  if (roleGroupId) url += `&roleGroupId=${encodeURIComponent(roleGroupId)}`;
  
  const response = await fetch(url, {
    method: "GET",
    headers: getHeaders(),
  });

  if (!response.ok) {
    throw new Error("Failed to fetch employees");
  }
  return response.json();
}

export async function updateEmployeeRole(
  id: number,
  roleGroupId: number
): Promise<void> {
  const request: EmployeeUpdateRequest = { roleGroupId };
  const response = await fetch(`${backendApiUrl}/employees/${id}`, {
    method: "PATCH",
    headers: getHeaders(),
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error("Failed to update employee role");
  }
}

export async function updateAccountStatus(
  id: number,
  accountStatus: "ACTIVE" | "LOCKED"
): Promise<void> {
  const request: AccountStatusUpdateRequest = { accountStatus };
  const response = await fetch(`${backendApiUrl}/employees/${id}/account-status`, {
    method: "PATCH",
    headers: getHeaders(),
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error("Failed to update account status");
  }
}

export async function issueAccount(id: number): Promise<any> {
  const response = await fetch(`${backendApiUrl}/employees/${id}/issue-account`, {
    method: "POST",
    headers: getHeaders(),
  });

  if (!response.ok) {
    throw new Error("Failed to issue account");
  }
  return response.json();
}

export async function deleteEmployees(ids: number[]): Promise<void> {
  const response = await fetch(`${backendApiUrl}/employees?ids=${ids.join(',')}`, {
    method: "DELETE",
    headers: getHeaders(),
  });

  if (!response.ok) {
    throw new Error("Failed to delete employees");
  }
}


export async function createEmployee(request: EmployeeCreateRequest): Promise<void> {
  const response = await fetch(`${backendApiUrl}/employees`, {
    method: "POST",
    headers: getHeaders(),
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error("Failed to create employee");
  }
}
