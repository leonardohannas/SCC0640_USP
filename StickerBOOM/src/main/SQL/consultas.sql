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
SELECT AVALIACAO_VENDA.COLECIONADOR_1, AVALIACAO_VENDA.DATA_HORA, AVALIACAO_VENDA.NEGOCIACAO, AVALIACAO_VENDA.COLECIONADOR_2 FROM AVALIACAO_VENDA JOIN VENDA
ON AVALIACAO_VENDA.NEGOCIACAO = VENDA.NEGOCIACAO AND AVALIACAO_VENDA.COLECIONADOR_2 = VENDA.COLECIONADOR
WHERE VENDA.VALOR = (SELECT MAX(VALOR) FROM VENDA);








