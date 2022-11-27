--             [ INTEGRANTES DO GRUPO ]                  --
--  Carlos Henrique Hannas de Carvalho  NºUSP: 11965988  --
--  Guilherme Azevedo Escudeiro         NºUSP: 11345600  --
--  Leonardo Hannas de Carvalho Santos  NºUSP: 11800480  --
--  Lucas Carvalho Freiberger Stapf     NºUSP: 11800559  --
-----------------------------------------------------------

-- Busca pela avaliação da venda de maior valor --
SELECT NEGOCIACAO, VALOR FROM VENDA
    WHERE VALOR = (SELECT MAX(VALOR) FROM VENDA);
--     achar na aval_venda o colecionador 1 e data_hora
--     achar na avaliacao o colecionador 1 e data_hora

-- Retorna a linha da tabela AVALIACAO_VENDA de maior valor de VENDA
SELECT AV.COLECIONADOR_1, AV.DATA_HORA, AV.NEGOCIACAO, AV.COLECIONADOR_2 FROM AVALIACAO_VENDA AV
    JOIN VENDA V ON AV.NEGOCIACAO = V.NEGOCIACAO AND AV.COLECIONADOR_2 = V.COLECIONADOR
    WHERE V.VALOR = (SELECT MAX(VALOR) FROM VENDA);


-- (JA ESTAO FUNCIONANDO)
-- Retorna a linha da tabela AVALIACAO de maior valor de VENDA
SELECT A.COLECIONADOR, A.DATA_HORA, A.NOTA, A.COMENTARIO FROM AVALIACAO A
    JOIN AVALIACAO_VENDA AV ON A.COLECIONADOR = AV.COLECIONADOR_1 AND A.DATA_HORA = AV.DATA_HORA
    JOIN VENDA V ON AV.NEGOCIACAO = V.NEGOCIACAO AND AV.COLECIONADOR_2 = V.COLECIONADOR
        WHERE V.VALOR = (SELECT MAX(VALOR) FROM VENDA);


-- Seleciona a média de completude do Álbum Virtual mais criado
SELECT ALBUM
    FROM ALBUM_VIRTUAL
    GROUP BY ALBUM, COLECIONADOR
    ORDER BY ALBUM DESC;

SELECT * FROM ALBUM_VIRTUAL;


































-- Retornar o Álbum Virtual mais criado e a sua média de completude
SELECT * FROM ALBUM_VIRTUAL
    GROUP BY COLECIONADOR, ALBUM
    ORDER BY ALBUM DESC;



