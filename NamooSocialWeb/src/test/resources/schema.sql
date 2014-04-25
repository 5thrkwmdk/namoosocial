DROP TABLE IF EXISTS user_tb RESTRICT;

DROP TABLE IF EXISTS message_tb RESTRICT;

DROP TABLE IF EXISTS usertouser_tb RESTRICT;

CREATE TABLE user_tb (
	userID   VARCHAR(20) PRIMARY KEY , 
	name     VARCHAR(20) NULL,   
	email    VARCHAR(40) NULL,     
	password VARCHAR(20) NULL      
);

CREATE TABLE message_tb (
	messageID INTEGER      PRIMARY KEY AUTO_INCREMENT, 
	contents  VARCHAR(500) NULL,    
	writerID  VARCHAR(20)  NULL,    
	REG_DT    TIMESTAMP    NULL      
);

CREATE TABLE usertouser_tb (
	who  VARCHAR(20) NOT NULL,
	whom VARCHAR(20) NOT NULL  
);

ALTER TABLE usertouser_tb
	ADD CONSTRAINT PK_usertouser_tb 
		PRIMARY KEY (
			who, 
			whom  
		);
