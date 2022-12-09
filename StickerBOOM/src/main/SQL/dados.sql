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
INSERT INTO CARGO VALUES ('678.901.234-56', 'USER');
INSERT INTO CARGO VALUES ('789.012.345-67', 'USER');


-- Insercao de Tuplas na Tabela Administrador --
INSERT INTO ADMINISTRADOR VALUES ('123.456.789-10', 'Everson Zoio', 'Rua Alisson Becker, 1');
INSERT INTO ADMINISTRADOR VALUES ('234.567.890-11', 'Mariano Filho', 'Rua Danilo Luiz da Silva, 2');
INSERT INTO ADMINISTRADOR VALUES ('567.890.123-45', 'Ronaldo de Assis Moreira', 'Avenida Edson Arantes do Nascimento, 10');

-- Insercao de Tuplas na Tabela Colecionador --
INSERT INTO COLECIONADOR VALUES ('345.678.901-12', 'Guilherme Arana', 'Alameda Alex Sandro, 6', DEFAULT);
INSERT INTO COLECIONADOR VALUES ('456.789.012-13', 'Junior Alonso', 'Avenida Marcos Aoas, 4', DEFAULT);
INSERT INTO COLECIONADOR VALUES ('678.901.234-56', 'Ronaldo de Assis Moreira', 'Rua Neymar Junior, 10', DEFAULT);
INSERT INTO COLECIONADOR VALUES ('789.012.345-67', 'Givanildo Barbosa', 'Rua Raphael Dias, 11', DEFAULT);


-- Insercao de Tuplas na Tabela Album --
INSERT INTO ALBUM VALUES (1234567890123, 'FIFA World Cup 1962', 600, '123.456.789-10', NULL);
INSERT INTO ALBUM VALUES (2345678901234, 'Pica-Pau', 50, '234.567.890-11', CURRENT_DATE);
INSERT INTO ALBUM VALUES (3456789012345, 'Pokémon', 270, '234.567.890-11', CURRENT_DATE);
INSERT INTO ALBUM VALUES (4456789012345, 'World Cup 2002 FIFA', 560, '234.567.890-11', CURRENT_DATE);
INSERT INTO ALBUM VALUES (1456789012345, 'Barbie', 130, '234.567.890-11', CURRENT_DATE);
INSERT INTO ALBUM VALUES (5456789012345, 'Tennis Cup', 90, '234.567.890-11', CURRENT_DATE);

-- Insercao de Tuplas na Tabela Figurinha --
INSERT INTO FIGURINHA VALUES (1234567890123, 'BR10', '123.456.789-10', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (2345678901234, '0007', '567.890.123-45', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (1234567890123, 'AG02', '123.456.789-10', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (2345678901234, '0078', '567.890.123-45', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (1234567890123, 'CR07', '123.456.789-10', CURRENT_DATE);
INSERT INTO FIGURINHA VALUES (2345678901234, '0012', '567.890.123-45', CURRENT_DATE);

-- Insercao de Tuplas na Tabela Banca --
INSERT INTO BANCA VALUES ('58.119.371/0001-77', 'Rua Thiago Silva, 3', '567.890.123-45');
INSERT INTO BANCA VALUES ('77.100.017/9113-85', 'Alameda Carlos Casemiro, 5', '234.567.890-11');

-- Insercao de Tuplas na Tabela Pacote_Fig --
INSERT INTO PACOTE_FIG VALUES (0123456789012, 5, 1234567890123);
INSERT INTO PACOTE_FIG VALUES (9876543210987, 5, 2345678901234);

-- Insercao de Tuplas na Tabela Pacote_Fig_Banca --
INSERT INTO PACOTE_FIG_BANCA VALUES (0123456789012, '58.119.371/0001-77');
INSERT INTO PACOTE_FIG_BANCA VALUES (9876543210987, '77.100.017/9113-85');

-- Insercao de Tuplas na Tabela Album_Banca --
INSERT INTO ALBUM_BANCA VALUES (1234567890123, '58.119.371/0001-77');
INSERT INTO ALBUM_BANCA VALUES (2345678901234, '77.100.017/9113-85');

-- Insercao de Tuplas na Tabela Album_Virtual --
INSERT INTO ALBUM_VIRTUAL VALUES ('345.678.901-12', 1234567890123);
INSERT INTO ALBUM_VIRTUAL VALUES ('456.789.012-13', 2345678901234);

-- Insercao de Tuplas na Tabela Colecionador_Figurinha --
INSERT INTO COLECIONADOR_FIGURINHA VALUES ('345.678.901-12', 1234567890123, 'BR10', DEFAULT);
INSERT INTO COLECIONADOR_FIGURINHA VALUES ('456.789.012-13', 2345678901234, '0007', 4);

-- Insercao de Tuplas na Tabela Album_Virtual_Figurinha --
INSERT INTO ALBUM_VIRTUAL_FIGURINHA VALUES ('345.678.901-12', 1234567890123, 1234567890123, 'BR10');
INSERT INTO ALBUM_VIRTUAL_FIGURINHA VALUES ('456.789.012-13', 2345678901234, 2345678901234, '0007');

-- Insercao de Tuplas na Tabela Banca_Figurinha --
INSERT INTO BANCA_FIGURINHA VALUES ('58.119.371/0001-77', 1234567890123, 'BR10');
INSERT INTO BANCA_FIGURINHA VALUES ('77.100.017/9113-85', 2345678901234, '0007');

-- Insercao de Tuplas na Tabela Negociacao --
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '345.678.901-12', 1234567890123, 'BR10', CURRENT_DATE, 5);
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '456.789.012-13', 2345678901234, '0007', CURRENT_DATE, 2); 
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '345.678.901-12', 1234567890123, 'AG02', CURRENT_DATE, 3);
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '456.789.012-13', 2345678901234, '0078', CURRENT_DATE, 4); 
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '345.678.901-12', 1234567890123, 'CR07', CURRENT_DATE, 2);
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '456.789.012-13', 2345678901234, '0012', CURRENT_DATE, 2);
INSERT INTO NEGOCIACAO VALUES (DEFAULT, '456.789.012-13', 1234567890123, 'BR10', CURRENT_DATE, 5);

-- Insercao de Tuplas na Tabela Venda --
INSERT INTO VENDA VALUES (1, '345.678.901-12', 12.90, 'Rua Alex Sandro, 6');
INSERT INTO VENDA VALUES (2, '456.789.012-13', 4.00, 'Rua dos Pombo, 7');
INSERT INTO VENDA VALUES (7, '456.789.012-13', 5.50, 'Alameda Victor Bagy, 83');

-- Insercao de Tuplas na Tabela Troca --
INSERT INTO TROCA VALUES (3, 4, 'Alameda Frederico Santos, 8');
INSERT INTO TROCA VALUES (5, 6, 'Avenida de Jesus, 9');

-- Insercao de Tuplas na Tabela Avaliacao --
INSERT INTO AVALIACAO VALUES ('345.678.901-12', TO_DATE('28-12-2000', 'DD-MM-YYYY'), 4.5, NULL, 'VENDA');
INSERT INTO AVALIACAO VALUES ('456.789.012-13', TO_DATE('14-03-1995', 'DD-MM-YYYY'), 2.3, 'Ruim', 'TROCA');
INSERT INTO AVALIACAO VALUES ('345.678.901-12', TO_DATE('01-07-1998', 'DD-MM-YYYY'), 5.0, 'Perfeito', 'TROCA');
INSERT INTO AVALIACAO VALUES ('456.789.012-13', TO_DATE('28-02-1997', 'DD-MM-YYYY'), 3.0, NULL, 'VENDA');

-- Insercao de Tuplas na Tabela Avaliacao_Venda --
INSERT INTO AVALIACAO_VENDA VALUES ('345.678.901-12', TO_DATE('28-12-2000', 'DD-MM-YYYY'), 2, '456.789.012-13');
INSERT INTO AVALIACAO_VENDA VALUES ('456.789.012-13', TO_DATE('28-02-1997', 'DD-MM-YYYY'), 1, '345.678.901-12');

-- Insercao de Tuplas na Tabela Avaliacao_Troca --
INSERT INTO AVALIACAO_TROCA VALUES ('456.789.012-13', TO_DATE('14-03-1995', 'DD-MM-YYYY'), 3, 4);
INSERT INTO AVALIACAO_TROCA VALUES ('345.678.901-12', TO_DATE('01-07-1998', 'DD-MM-YYYY'), 5, 6);

-- Insercao de Tuplas na Tabela Banimento --
INSERT INTO BANIMENTO VALUES ('123.456.789-10', '678.901.234-56', CURRENT_DATE, 48);
INSERT INTO BANIMENTO VALUES ('234.567.890-11', '789.012.345-67', CURRENT_DATE, 72);
