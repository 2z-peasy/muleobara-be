/* 예시 파일 */

-- 건강/식습관 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (1, '비건 식단', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (2, '페스코 식단', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (3, '저당식이', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (4, '고단백식이', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (5, '다이어트', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (6, '체중 유지', '건강/식습관', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 생활패턴/라이프스타일 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (7, '야생성', '생활패턴/라이프스타일', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (8, '아침형 인간', '생활패턴/라이프스타일', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (9, '여행 지향', '생활패턴/라이프스타일', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (10, '주말 활동형', '생활패턴/라이프스타일', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 환경보호 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (11, '지속 가능성', '환경보호', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (12, '저탄소 생활', '환경보호', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (13, '제로 웨이스트', '환경보호', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (14, '친환경 제품', '환경보호', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 자기 관리/개발 목표 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (15, '독서 목표', '자기 관리/개발 목표', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (16, '자기계발', '자기 관리/개발 목표', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (17, '운동 목표', '자기 관리/개발 목표', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 시간 관리 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (18, '시간 절약', '시간 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (19, '마감 준수', '시간 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (20, '멀티 테스킹', '시간 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 예산/재정 관리 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (21, '절약 모드', '예산/재정 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (22, '평소 지출', '예산/재정 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (23, '가성비 지향', '예산/재정 관리', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 관계/사회적 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (24, '가족 지향', '관계/사회적', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (25, '친구 지향', '관계/사회적', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (26, '네트워킹', '관계/사회적', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (27, '혼자 활동', '관계/사회적', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';

-- 취미/여가 카테고리 추가
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (28, '운동 취미', '취미/여가', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (29, '예술 취미', '취미/여가', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (30, '게임 선호', '취미/여가', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
INSERT INTO GOALS (id, goal_name, category, used_yn) VALUES (31, '음악 감상', '취미/여가', 'Y')
    ON DUPLICATE KEY UPDATE used_yn = 'Y';
