-- �����
DROP TABLE IF EXISTS user_tb RESTRICT;

-- �޽���
DROP TABLE IF EXISTS message_tb RESTRICT;

-- ����ڰ��
DROP TABLE IF EXISTS usertouser_tb RESTRICT;

-- �����
CREATE TABLE user_tb (
	userID   VARCHAR(20) NOT NULL, -- ����ھ��̵�
	name     VARCHAR(20) NULL,     -- �̸�
	email    VARCHAR(40) NULL,     -- �̸���
	password VARCHAR(20) NULL      -- ��й�ȣ
);

-- �����
ALTER TABLE user_tb
	ADD CONSTRAINT PK_user_tb -- ����� �⺻Ű
		PRIMARY KEY (
			userID -- ����ھ��̵�
		);

-- �޽���
CREATE TABLE message_tb (
	messageID INTEGER      NOT NULL, -- �޽������̵�
	contents  VARCHAR(500) NULL,     -- ����
	writerID  VARCHAR(20)  NULL,     -- �ۼ���
	REG_DT    TIMESTAMP    NULL      -- �ۼ��Ͻ�
);

-- �޽���
ALTER TABLE message_tb
	ADD CONSTRAINT PK_message_tb -- �޽��� �⺻Ű
		PRIMARY KEY (
			messageID -- �޽������̵�
		);

ALTER TABLE message_tb
	MODIFY COLUMN messageID INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE message_tb
	AUTO_INCREMENT = 1;

-- ����ڰ��
CREATE TABLE usertouser_tb (
	who  VARCHAR(20) NOT NULL, -- ����
	whom VARCHAR(20) NOT NULL  -- ������
);

-- ����ڰ��
ALTER TABLE usertouser_tb
	ADD CONSTRAINT PK_usertouser_tb -- ����ڰ�� �⺻Ű
		PRIMARY KEY (
			who,  -- ����
			whom  -- ������
		);

-- �޽���
ALTER TABLE message_tb
	ADD CONSTRAINT FK_user_tb_TO_message_tb -- ����� -> �޽���
		FOREIGN KEY (
			writerID -- �ۼ���
		)
		REFERENCES user_tb ( -- �����
			userID -- ����ھ��̵�
		);