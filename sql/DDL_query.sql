-- 블로그
ALTER TABLE `blog`
	DROP FOREIGN KEY `FK_user_TO_blog`; -- 회원 -> 블로그

-- 카테고리
ALTER TABLE `category`
	DROP FOREIGN KEY `FK_blog_TO_category`; -- 블로그 -> 카테고리

-- 포스트
ALTER TABLE `post`
	DROP FOREIGN KEY `FK_category_TO_post`; -- 카테고리 -> 포스트

-- 댓글
ALTER TABLE `comment`
	DROP FOREIGN KEY `FK_post_TO_comment`; -- 포스트 -> 댓글

-- 회원
DROP TABLE IF EXISTS `user` RESTRICT;

-- 블로그
DROP TABLE IF EXISTS `blog` RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS `category` RESTRICT;

-- 포스트
DROP TABLE IF EXISTS `post` RESTRICT;

-- 댓글
DROP TABLE IF EXISTS `comment` RESTRICT;

-- 회원
CREATE TABLE `user` (
	`id`       VARCHAR(50) NOT NULL, -- 아이디
	`name`     VARCHAR(50) NOT NULL, -- 이름
	`password` VARCHAR(64) NOT NULL, -- 패스워드
	`reg_date` DATETIME    NULL      -- 가입일
);

-- 회원
ALTER TABLE `user`
	ADD CONSTRAINT `PK_user` -- 회원 기본키
		PRIMARY KEY (
			`id` -- 아이디
		);

-- 블로그
CREATE TABLE `blog` (
	`user_id` VARCHAR(50)  NOT NULL, -- 회원아이디
	`title`   VARCHAR(100) NOT NULL, -- 제목
	`image`   VARCHAR(100) NULL      -- 이미지
);

-- 블로그
ALTER TABLE `blog`
	ADD CONSTRAINT `PK_blog` -- 블로그 기본키
		PRIMARY KEY (
			`user_id` -- 회원아이디
		);

-- 카테고리
CREATE TABLE `category` (
	`no`          INTEGER      NOT NULL, -- 번호
	`name`        VARCHAR(50)  NOT NULL, -- 이름
	`description` VARCHAR(256) NULL,     -- 설명
	`post_count`  INTEGER      NOT NULL, -- 포스트수
	`reg_date`    DATETIME     NULL,     -- 등록일
	`user_id`     VARCHAR(50)  NULL      -- 회원아이디
);

-- 카테고리
ALTER TABLE `category`
	ADD CONSTRAINT `PK_category` -- 카테고리 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `category`
	MODIFY COLUMN `no` INTEGER NOT NULL AUTO_INCREMENT;

-- 포스트
CREATE TABLE `post` (
	`no`          INTEGER       NOT NULL, -- 번호
	`category_no` INTEGER       NULL     DEFAULT 1, -- 카테고리번호
	`title`       VARCHAR(100)  NOT NULL, -- 제목
	`body`        VARCHAR(1024) NULL,     -- 내용
	`reg_date`    DATETIME      NULL      -- 등록일
);

-- 포스트
ALTER TABLE `post`
	ADD CONSTRAINT `PK_post` -- 포스트 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `post`
	MODIFY COLUMN `no` INTEGER NOT NULL AUTO_INCREMENT;

-- 댓글
CREATE TABLE `comment` (
	`no`       INTEGER      NOT NULL, -- 번호
	`body`     VARCHAR(256) NOT NULL, -- 내용
	`post_no`  INTEGER      NOT NULL, -- 포스트번호
	`reg_date` DATETIME     NULL      -- 등록일
);

-- 댓글
ALTER TABLE `comment`
	ADD CONSTRAINT `PK_comment` -- 댓글 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `comment`
	MODIFY COLUMN `no` INTEGER NOT NULL AUTO_INCREMENT;

-- 블로그
ALTER TABLE `blog`
	ADD CONSTRAINT `FK_user_TO_blog` -- 회원 -> 블로그
		FOREIGN KEY (
			`user_id` -- 회원아이디
		)
		REFERENCES `user` ( -- 회원
			`id` -- 아이디
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 카테고리
ALTER TABLE `category`
	ADD CONSTRAINT `FK_blog_TO_category` -- 블로그 -> 카테고리
		FOREIGN KEY (
			`user_id` -- 회원아이디
		)
		REFERENCES `blog` ( -- 블로그
			`user_id` -- 회원아이디
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 포스트
ALTER TABLE `post`
	ADD CONSTRAINT `FK_category_TO_post` -- 카테고리 -> 포스트
		FOREIGN KEY (
			`category_no` -- 카테고리번호
		)
		REFERENCES `category` ( -- 카테고리
			`no` -- 번호
		)
		ON DELETE SET NULL
		ON UPDATE CASCADE;

-- 댓글
ALTER TABLE `comment`
	ADD CONSTRAINT `FK_post_TO_comment` -- 포스트 -> 댓글
		FOREIGN KEY (
			`post_no` -- 포스트번호
		)
		REFERENCES `post` ( -- 포스트
			`no` -- 번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;
        
SELECT * FROM user;
SELECT * FROM blog;
SELECT * FROM category;

INSERT INTO user
	   VALUES('dooly@a.com', '둘리', PASSWORD(123), now());

DELETE FROM user WHERE id='hong@a.com';
DELETE FROM user WHERE id='dooly@a.com';
DELETE FROM blog;

UPDATE blog
   SET image='/assets/images/default.png'
 WHERE user_id='hong';
 
 INSERT INTO category
	  VALUES(null, '미분류', '분류하지 않은 카테고리입니다.', 0, now(), 'hong');
 
 SELECT no,
 	    name,
	    description,
	    post_count as postCount,
	    DATE_FORMAT(reg_date, '%Y-%m-%d %h:%i:%s') as regDate,
	    user_id as userId
   FROM category
  WHERE user_id = 'hong';
  
  
INSERT INTO post
	 VALUES(null, 1, 'Test 포스트', '홍길동 테스트', now());
INSERT INTO post
	 VALUES(null, 2, 'Test 포스트', '송이버섯 테스트', now());
     
SELECT no, category_no, title, body, reg_date
  FROM post
 WHERE category_no IN (SELECT no
						 FROM category
						WHERE user_id = 'hong');
                        
                        
SELECT no,
	   category_no as categoryNo,
	   title,
	   body,
	   DATE_FORMAT(reg_date, '%Y-%m-%d %H:%i:%s') as regDate
  FROM post, category
 WHERE post.category_no = category.no
   AND post.category_no = 16
   AND category.user_id = 'hong'
ORDER BY no DESC;