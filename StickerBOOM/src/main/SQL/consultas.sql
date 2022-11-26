--             [ INTEGRANTES DO GRUPO ]                  --
--  Carlos Henrique Hannas de Carvalho  NºUSP: 11965988  --
--  Guilherme Azevedo Escudeiro         NºUSP: 11345600  --
--  Leonardo Hannas de Carvalho Santos  NºUSP: 11800480  --
--  Lucas Carvalho Freiberger Stapf     NºUSP: 11800559  --
-----------------------------------------------------------

-- Busca pela avaliação da venda de maior valor --
SELECT NEGOCIACAO, VALOR FROM VENDA
    WHERE VALOR = (SELECT MAX(VALOR) FROM VENDA)
    -- achar na aval_venda o colecionador 1 e data_hora
    -- achar na avaliacao o colecionador 1 e data_hora