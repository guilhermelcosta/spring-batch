CREATE DATABASE IF NOT EXISTS migracao_dados;

DROP TABLE IF EXISTS migracao_dados.pessoa;
CREATE TABLE migracao_dados.pessoa
(
    id              INT PRIMARY KEY,
    nome            VARCHAR(500),
    email           VARCHAR(500),
    data_nascimento DATETIME,
    idade           INT
);

DROP TABLE IF EXISTS migracao_dados.dados_bancarios;
CREATE TABLE migracao_dados.dados_bancarios
(
    id        INT PRIMARY KEY,
    pessoa_id INT,
    agencia   INT,
    conta     INT,
    banco     INT
);
