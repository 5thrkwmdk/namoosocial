-- �����
DROP TABLE IF EXISTS USER_TB RESTRICT;

-- �޽���
DROP TABLE IF EXISTS MESSAGE_TB RESTRICT;

-- ����ڰ���
DROP TABLE IF EXISTS UserTOUser_TB RESTRICT;

-- �����
CREATE TABLE USER_TB (
	userID   VARCHAR(20) NOT NULL, -- ����ھ��̵�
	name     VARCHAR(20) NULL,     -- �̸�
	email    VARCHAR(40) NULL,     -- �̸���
	password VARCHAR(20) NULL      -- ��й�ȣ
);

-- �����
ALTER TABLE USER_TB
	ADD CONSTRAINT PK_USER_TB -- ����� �⺻Ű
		PRIMARY KEY (
			userID -- ����ھ��̵�
		);

-- �޽���
CREATE TABLE MESSAGE_TB (
	messageID INTEGER      NOT NULL, -- �޽������̵�
	contents  VARCHAR(500) NULL,     -- ����
	writerID  VARCHAR(20)  NULL,     -- �ۼ���
	REG_DT    TIMESTAMP    NULL      -- �ۼ��Ͻ�
);

-- �޽���
ALTER TABLE MESSAGE_TB
	ADD CONSTRAINT PK_MESSAGE_TB -- �޽��� �⺻Ű
		PRIMARY KEY (
			messageID -- �޽������̵�
		);

ALTER TABLE MESSAGE_TB
	MODIFY COLUMN messageID INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE MESSAGE_TB
	AUTO_INCREMENT = 1;

-- ����ڰ���
CREATE TABLE UserTOUser_TB (
	who  VARCHAR(20) NOT NULL, -- ����
	whom VARCHAR(20) NOT NULL  -- ������
);

-- ����ڰ���
ALTER TABLE UserTOUser_TB
	ADD CONSTRAINT PK_UserTOUser_TB -- ����ڰ��� �⺻Ű
		PRIMARY KEY (
			who,  -- ����
			whom  -- ������
		);

-- �޽���
ALTER TABLE MESSAGE_TB
	ADD CONSTRAINT FK_USER_TB_TO_MESSAGE_TB -- ����� -> �޽���
		FOREIGN KEY (
			writerID -- �ۼ���
		)
		REFERENCES USER_TB ( -- �����
			userID -- ����ھ��̵�
		);