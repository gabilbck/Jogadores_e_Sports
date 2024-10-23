-- Cria a tabela de jogadores
CREATE TABLE jogador (
      id INT NOT NULL AUTO_INCREMENT, 
      nome VARCHAR(100), 
      salario DOUBLE NOT NULL, 
      experiencia INT NOT NULL, 
      categoria INT, 
      PRIMARY KEY(idP), 
      FOREIGN KEY (categoria) REFERENCES tipo_jogo(id));

-- cria a tabela de categoria de jogo que os jogadores vão estar ligados
CREATE TABLE tipo_jogo (
      id INT NOT NULL AUTO_INCREMENT, 
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

/*
criando dois jogadores base/exemplo:
treinee que joga na categoria de grupo
veterno que joga na categoria solo
*/
-- não é necessário passar um id, pois os campos de PK são Auto_increment
INSERT INTO jogador(id, nome, salario, experiencia, categoria)
VALUES ("", "João", "3500.00", "2", "2");

INSERT INTO jogador(id, nome, salario, experiencia, categoria)
VALUES ("", "Lucas", "9500.00", "6", "1");
