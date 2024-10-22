CREATE TABLE jogador (id_jogador INT NOT NULL, nome_jogador VARCHAR(100), salario_jogador DOUBLE NOT NULL, experiencia_jogador INT NOT NULL, PRIMARY KEY(id_jogador));

CREATE TABLE tipo_jogo (cod_tipo INT NOT NULL, categoria VARCHAR(2), descricao VARCHAR(100), PRIMARY KEY(cod_tipo));
