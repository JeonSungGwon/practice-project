INSERT INTO article(id, title, content) VALUES (1,'wjs4518@naver.com','qwer');
INSERT INTO article(id, title, content) VALUES (2,'wjs45@naver.com','asdf');
INSERT INTO article(id, title, content) VALUES (3,'wjs18@naver.com','zxcv');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4,'wjs4518@sdfsfdsdf','q1231er');
INSERT INTO article(id, title, content) VALUES (5,'wjs45@sadfsadf.com','asd5wqef');
INSERT INTO article(id, title, content) VALUES (6,'wjs18@dfgdf.com','zxcretrev');

--comment 더미 데이터
-- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1,4,'개구리','배블');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2,4,'고양이','개굴');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3,4,'말랑이','마블');

INSERT INTO comment(id, article_id, nickname, body) VALUES (4,5,'개구리','배블');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5,5,'고양이','개굴');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6,5,'말랑이','마블');

INSERT INTO comment(id, article_id, nickname, body) VALUES (7,6,'개구리','배블');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8,6,'고양이','개굴');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9,6,'말랑이','마블');