CREATE
DATABASE IF NOT EXISTS dados_bancarios;

DROP TABLE IF EXISTS dados_bancarios.pessoa;
CREATE TABLE dados_bancarios.pessoa
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(500),
    email           VARCHAR(500),
    data_nascimento VARCHAR(500),
    idade           INT
);

INSERT INTO dados_bancarios.pessoa VALUES (0, 'Guilherme', 'guilherme@email.com', '20/04/1997', '26');
