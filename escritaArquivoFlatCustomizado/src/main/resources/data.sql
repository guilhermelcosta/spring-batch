CREATE DATABASE IF NOT EXISTS empresa_ficticia;
CREATE TABLE IF NOT EXISTS empresa_ficticia.lancamento
(
    id INT PRIMARY KEY,
    codigoNaturezaDespesa INT,
    descricaoNaturezaDespesa TEXT,
    descricaoLancamento TEXT,
    dataLancamento DATE,
    valorLancamento DOUBLE
);

/*
  O IGNORE ignora respostas de erro que podem ser geradas pelo INSERT.
  No caso abaixo, cada lançamento é mapeado por um id (PK). Caso o id
  já tenha sido cadastrado previamente, o SQL emite uma mensagem de erro
  informando isso. O IGNORE serve para que caso ocorra um erro, ele
  não pare a execução do programa e apenas o siga executando.
*/

INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (1,44905224,'EQUIPAMENTO DE PROTEÇÃO SEGURANÇA E SOCORRO','Alarme','2020-05-01',1000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (2, 44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Cortina de sala','2020-05-02',1000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (3, 44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Bandeiras','2020-05-03',500);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (4, 44905287,'MATERIAL DE CONSUMO DE USO DURADOURO','Guarda Sol','2020-05-04',500);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (5, 33903016,'MATERIAL DE EXPEDIENTE','Resma de Papel A4','2020-05-01',2000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (6, 33903016,'MATERIAL DE EXPEDIENTE','Cartucho Impressora','2020-05-10',2000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (7, 44905218,'COLEÇÕES E MATERIAIS BIBLIOGRÁFICOS','Dicionários','2020-05-12',4000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (8, 44905218,'COLEÇÕES E MATERIAIS BIBLIOGRÁFICOS','Livro de Algoritmos','2020-05-11',4000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (9, 33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Amianto','2020-05-13',8000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (10, 33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Aparelhos Sanitários','2020-05-11',6000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (11, 33903024,'MATERIAL P/ MANUT. DE BENS IMÓVEIS/INSTALAÇÕES','Cimento','2020-05-12',2000);
INSERT IGNORE INTO empresa_ficticia.lancamento VALUES (12, 33903302,'PASSAGENS PARA O EXTERIOR','Viagem para Las Vegas','2020-05-01',32000);

