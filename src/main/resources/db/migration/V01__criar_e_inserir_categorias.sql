CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Água');
INSERT INTO categoria (nome) values ('Energia');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Internet');
INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Outros');
INSERT INTO categoria (nome) values ('Plano de Saúde');
INSERT INTO categoria (nome) values ('Supermercado');




