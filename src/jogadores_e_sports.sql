-- Cria a tabela de jogadores
CREATE TABLE jogador (
      id INT NOT NULL, 
      nome VARCHAR(100), 
      salario DOUBLE NOT NULL, 
      experiencia INT NOT NULL, 
      categoria INT, 
      PRIMARY KEY(idP), 
      FOREIGN KEY (categoria) REFERENCES tipo_jogo(id));

-- cria a tabela de categoria de jogo que os jogadores vão estar ligados
CREATE TABLE tipo_jogo (
      id INT NOT NULL, 
      categoria VARCHAR(2), 
      descricao VARCHAR(100), 
      PRIMARY KEY(id));

/*
criando as duas categorias:
id->: 1 - para solo
id-> 2 - para em grupo
*/

INSERT INTO tipo_jogo (id, categoria, descricao)
VALUES ("1", "Solo", "Nesta categoria os jogadores jogam de forma individual. Times são proibidos.");

INSERT INTO tipo_jogo (id, categoria, descricao)
VALUES ("2", "Grupo", "Nesta categoria os jogadores jogam acompanhados.");
