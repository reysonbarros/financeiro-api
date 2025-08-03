CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario(email,password) values("teste@qualidade.com", "$2a$10$bX.2jsjRHXNsTwkzIs7k/u03mI4RX3R8fkd7yZq9Pm/7VaeqP/vNO");
