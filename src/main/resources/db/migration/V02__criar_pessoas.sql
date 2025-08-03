CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cpf_cnpj VARCHAR(20),
	nome VARCHAR(50) NOT NULL,
	perfil CHAR(2),
	tipo VARCHAR(20),
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	codigo_cidade BIGINT(20),
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;