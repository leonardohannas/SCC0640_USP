--             [ INTEGRANTES DO GRUPO ]                  --
--  Carlos Henrique Hannas de Carvalho  NºUSP: 11965988  --
--  Guilherme Azevedo Escudeiro         NºUSP: 11345600  --
--  Leonardo Hannas de Carvalho Santos  NºUSP: 11800480  --
--  Lucas Carvalho Freiberger Stapf     NºUSP: 11800559  --
-----------------------------------------------------------


-- Consulta 1: Retorna a linha da tabela AVALIACAO_VENDA de maior valor de VENDA
SELECT AV.COLECIONADOR_1, AV.DATA_HORA, AV.NEGOCIACAO, AV.COLECIONADOR_2, V.VALOR FROM AVALIACAO_VENDA AV
    JOIN VENDA V ON AV.NEGOCIACAO = V.NEGOCIACAO AND AV.COLECIONADOR_2 = V.COLECIONADOR
    WHERE V.VALOR = (SELECT MAX(VALOR) FROM VENDA);


-- Consulta 2: Retorna a tabela os valores dos atributos [Nota] e [Comentário] da AVALIACAO de maior valor de VENDA
    SELECT NOTA, COMENTARIO
        FROM VENDA V JOIN AVALIACAO_VENDA AV
            ON V.NEGOCIACAO = AV.NEGOCIACAO AND V.VALOR = (SELECT MAX(VALOR) FROM VENDA)
            JOIN AVALIACAO A
                ON A.COLECIONADOR = AV.COLECIONADOR_1 AND A.DATA_HORA = AV.DATA_HORA;


-- Consulta 3: Seleciona FIGURINHA de alguma Copa vendida pela BANCA localizada na 'Rua Thiago Silva, 3'
SELECT BF.ALBUM, BF.IDENTIFICADOR, COUNT(*)
FROM BANCA_FIGURINHA BF JOIN BANCA B
     ON BF.BANCA = B.CNPJ
     JOIN ALBUM A ON BF.ALBUM = A.ISBN
WHERE UPPER(B.ENDERECO) = 'RUA THIAGO SILVA, 3' AND UPPER(A.TITULO) LIKE '%CUP%'
GROUP BY BF.ALBUM, BF.IDENTIFICADOR;


-- Consulta 4: Seleciona a média de completude dos albuns virtuais do sistema
SELECT AL.ISBN, AL.TITULO, TOTAL_FIG / MAX_FIG AS "Completude do Album"
    FROM (SELECT AVF.ALBUM_V, COUNT(*) AS "TOTAL_FIG"
        FROM ALBUM_VIRTUAL_FIGURINHA AVF
        GROUP BY AVF.ALBUM_V) TABELA_MF
        JOIN (SELECT A.ISBN, SUM(A.NROFIGURINHAS) AS "MAX_FIG"
            FROM ALBUM A JOIN ALBUM_VIRTUAL AV
                ON A.ISBN = AV.ALBUM
            GROUP BY A.ISBN) TABELA_TF
            ON TABELA_MF.ALBUM_V = TABELA_TF.ISBN
            JOIN ALBUM AL
                ON AL.ISBN = TABELA_TF.ISBN;


-- Consulta 5: Seleciona os colecionadores ([CPF] e [Nome]) que colecionam todos os álbuns cadastrados no sistema
SELECT DISTINCT AV1.COLECIONADOR, C.NOME FROM ALBUM_VIRTUAL AV1
JOIN COLECIONADOR C ON AV1.COLECIONADOR = C.CPF
WHERE NOT EXISTS (
        SELECT ISBN FROM ALBUM
        MINUS
        SELECT AV2.ALBUM FROM ALBUM_VIRTUAL AV2
        WHERE AV1.COLECIONADOR = AV2.COLECIONADOR);
