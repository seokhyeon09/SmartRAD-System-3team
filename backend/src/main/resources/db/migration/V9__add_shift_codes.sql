INSERT INTO common_code (code, group_code, name, description, is_active, sort_order, created_at, updated_at) VALUES 
('D', 'SHIFT', '데이', '데이 근무 (07:00 ~ 15:00)', TRUE, 1, NOW(), NOW()),
('E', 'SHIFT', '이브닝', '이브닝 근무 (15:00 ~ 23:00)', TRUE, 2, NOW(), NOW()),
('N', 'SHIFT', '나이트', '나이트 근무 (23:00 ~ 07:00)', TRUE, 3, NOW(), NOW()),
('OFF', 'SHIFT', '오프', '휴무', TRUE, 4, NOW(), NOW());
