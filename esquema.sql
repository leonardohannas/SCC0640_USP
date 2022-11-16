--             [ INTEGRANTES DO GRUPO ]                  --
--  Carlos Henrique Hannas de Carvalho  NºUSP: 11965988  --
--  Guilherme Azevedo Escudeiro         NºUSP: 11345600  --
--  Leonardo Hannas de Carvalho Santos  NºUSP: 11800480  --
--  Lucas Carvalho Freiberger Stapf     NºUSP: 11800559  --
-----------------------------------------------------------


-- Tabela Cargo --
CREATE TABLE CARGO (
    CPF CHAR(14) NOT NULL,
    CARGO CHAR(4) NOT NULL,
    CHECK (UPPER(CARGO) IN ('ADM', 'USER')),
    CONSTRAINT PK_CPF PRIMARY KEY (CPF)
);