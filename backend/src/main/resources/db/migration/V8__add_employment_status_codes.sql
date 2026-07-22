-- 8. 재직 상태(EMP_STS) 관련 공통 코드 및 테스트 이력 추가

-- 공통 코드 그룹 추가 여부 (CommonCode는 group_code도 자체 컬럼이므로 그냥 넣으면 됨)
INSERT INTO common_code (code, group_code, name, description, is_active, sort_order, created_at, updated_at) VALUES 
('STS_ACTIVE', 'EMP_STS', '재직', '현재 정상 근무 중인 상태', TRUE, 1, NOW(), NOW()),
('STS_LEAVE', 'EMP_STS', '휴직', '육아휴직, 병가 등 장기 휴직 상태', TRUE, 2, NOW(), NOW()),
('STS_RETIRE', 'EMP_STS', '퇴직', '퇴사자', TRUE, 3, NOW(), NOW());

-- 테스트를 위해 사원 한 명을 더 추가하고, 그 사원에게 '휴직' 이력을 하나 넣어준다.
-- 사번: RN-2001 (신규 간호사), 부서는 중환자실(3)
INSERT INTO employee (emp_no, name, password, email, phone, join_date, is_shift_worker, role_group_id, department_id, position_code, job_category_code, created_at, updated_at) 
VALUES ('RN-2001', '이휴직', '1234', 'rn2001@tphr.com', '010-2001-2001', '2026-05-01', TRUE, (SELECT id FROM role_group WHERE name = '일반직원'), 3, 'POS_03', 'JOB_02', NOW(), NOW());

-- 이휴직 사원(ID: 3)은 2026년 8월 10일부터 8월 30일까지 휴직(STS_LEAVE) 처리
INSERT INTO employment_history (employee_id, type_code, start_date, end_date, reason, created_at, updated_at) 
VALUES (
    (SELECT id FROM employee WHERE emp_no = 'RN-2001'), 
    'STS_LEAVE', 
    '2026-08-10', 
    '2026-08-30', 
    '건강상 이유로 3주 휴직', 
    NOW(), 
    NOW()
);
