--             [ INTEGRANTES DO GRUPO ]                  --
--  Carlos Henrique Hannas de Carvalho  NºUSP: 11965988  --
--  Guilherme Azevedo Escudeiro         NºUSP: 11345600  --
--  Leonardo Hannas de Carvalho Santos  NºUSP: 11800480  --
--  Lucas Carvalho Freiberger Stapf     NºUSP: 11800559  --
-----------------------------------------------------------

-- Insercao de Tuplas na Tabela Cargo --
INSERT INTO CARGO VALUES ('123.456.789-10', 'ADM');
INSERT INTO CARGO VALUES ('234.567.890-11', 'ADM');
INSERT INTO CARGO VALUES ('567.890.123-45', 'ADM');
INSERT INTO CARGO VALUES ('345.678.901-12', 'USER');
INSERT INTO CARGO VALUES ('456.789.012-13', 'USER');



-- Insercao de Tuplas na Tabela Administrador --
INSERT INTO ADMINISTRADOR VALUES ('123.456.789-10', 'Everson Zoio', 'Rua Alisson Becker, 1');
INSERT INTO ADMINISTRADOR VALUES ('234.567.890-11', 'Mariano Filho', 'Rua Danilo Luiz da Silva, 2');
INSERT INTO ADMINISTRADOR VALUES ('567.890.123-45', 'Ronaldo de Assis Moreira', 'Avenida Edson Arantes do Nascimento, 10');


-- Insercao de Tuplas na Tabela Colecionador --
INSERT INTO COLECIONADOR VALUES ('345.678.901-12', 'Guilherme Arana', 'Alameda Alex Sandro, 6', DEFAULT);
INSERT INTO COLECIONADOR VALUES ('456.789.012-13', 'Junior Alonso', 'Avenida Marcos Aoas, 4', DEFAULT);

-- Insercao de Tuplas na Tabela Album --
INSERT INTO ALBUM VALUES (1234567890123, 'FIFA World Cup 1962', 600, '123.456.789-10', NULL);
INSERT INTO ALBUM VALUES (2345678901234, 'Pica-Pau', 50, '234.567.890-11', CURRENT_DATE);

-- Insercao de Tuplas na Tabela Figurinha --
INSERT INTO FIGURINHA VALUES (1234567890123, BR10, '123.456.789-10', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (2345678901234, 0007, '567.890.123-45', CURRENT_DATE);

-- Insercao de Tuplas na Tabela Banca --
INSERT INTO BANCA VALUES ('58.119.371/0001-77', 'Rua Thiago Silva, 3', '567.890.123-45');
INSERT INTO BANCA VALUES ('77.100.017/9113-85', 'Alameda Carlos Casemiro, 5', '234.567.890-11');


