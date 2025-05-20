INSERT INTO notification_templates (id, code, title, category, message_template, link_to) VALUES
-- 질문
(1, 'TICKET_EMPTY'           ,'질문권이 부족해요!'   ,'TICKET'    ,'질문권이 부족해요! 미션을 완료하고 추가 질문권을 받아보세요!', '/ai/chat'),
(2, 'TICKET_REFILL'          ,'질문권이 충전되었어요!','TICKET'    ,'기본 질문권이 충전되었어요!'                         , '/ai/chat'),
-- 미션
(3, 'MISSION_REFRESH'        ,'오늘의 미션 업데이트'  ,'MISSION'   ,'오늘의 미션이 업데이트되었어요!'                      , '/mission/today'),
-- 커뮤니티
(4, 'COMMUNITY_VOTE_NEW'     ,'새로운 투표가 올라왔어요','COMMUNITY','새로운 투표가 올라왔어요! 당신의 선택은?'               , '/community'),
(5, 'COMMUNITY_VOTE_REACTION','투표 반응 알림'       ,'COMMUNITY','당신의 고민에 많은 사람들이 참여하고 있어요!'            , '/community/myposts'),
-- 댓글
(6, 'COMMENT_NEW'            ,'댓글이 달렸어요'       ,'COMMENT' ,'누군가 당신의 고민에 댓글을 남겼어요.'                  , '/community/post/{postId}'),
-- 밸런스 게임
(7, 'BALANCE_REMINDER'       ,'밸런스 게임 참여 유도'  ,'BALANCE' ,'오늘의 밸런스 게임을 놓치지 마세요!'                    , '/balancegame'),
-- 시스템
(8, 'SYSTEM_UPDATE'          ,'앱 업데이트 안내'      ,'SYSTEM'  ,'새로운 버전이 출시되었어요. 지금 업데이트 해보세요!'       , 'https://store.example.com/app'),
(9, 'SYSTEM_MAINTENANCE'     ,'긴급 점검 안내'       ,'SYSTEM'  ,'오늘 02시에 서버 점검이 예정되어 있습니다.'              , NULL),
-- 이벤트
(10,'EVENT_STARTED'          ,'이벤트 시작 알림'      ,'EVENT'  ,'한정 이벤트가 시작되었어요! 지금 확인하세요!'             , '/event');