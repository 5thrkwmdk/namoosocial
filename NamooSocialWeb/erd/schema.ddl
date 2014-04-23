-- 사용자
DROP TABLE IF EXISTS USER_TB RESTRICT;

-- 메시지
DROP TABLE IF EXISTS MESSAGE_TB RESTRICT;

-- 사용자관계
DROP TABLE IF EXISTS UserTOUser_TB RESTRICT;

-- 사용자
CREATE TABLE USER_TB (
	userID   VARCHAR(20) NOT NULL, -- 사용자아이디
	name     VARCHAR(20) NULL,     -- 이름
	email    VARCHAR(40) NULL,     -- 이메일
	password VARCHAR(20) NULL      -- 비밀번호
);

-- 사용자
ALTER TABLE USER_TB
	ADD CONSTRAINT PK_USER_TB -- 사용자 기본키
		PRIMARY KEY (
			userID -- 사용자아이디
		);

-- 메시지
CREATE TABLE MESSAGE_TB (
	messageID INTEGER      NOT NULL, -- 메시지아이디
	contents  VARCHAR(500) NULL,     -- 내용
	writerID  VARCHAR(20)  NULL,     -- 작성자
	REG_DT    TIMESTAMP    NULL      -- 작성일시
);

-- 메시지
ALTER TABLE MESSAGE_TB
	ADD CONSTRAINT PK_MESSAGE_TB -- 메시지 기본키
		PRIMARY KEY (
			messageID -- 메시지아이디
		);

ALTER TABLE MESSAGE_TB
	MODIFY COLUMN messageID INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE MESSAGE_TB
	AUTO_INCREMENT = 1;

-- 사용자관계
CREATE TABLE UserTOUser_TB (
	who  VARCHAR(20) NOT NULL, -- 누가
	whom VARCHAR(20) NOT NULL  -- 누구를
);

-- 사용자관계
ALTER TABLE UserTOUser_TB
	ADD CONSTRAINT PK_UserTOUser_TB -- 사용자관계 기본키
		PRIMARY KEY (
			who,  -- 누가
			whom  -- 누구를
		);

-- 메시지
ALTER TABLE MESSAGE_TB
	ADD CONSTRAINT FK_USER_TB_TO_MESSAGE_TB -- 사용자 -> 메시지
		FOREIGN KEY (
			writerID -- 작성자
		)
		REFERENCES USER_TB ( -- 사용자
			userID -- 사용자아이디
		);