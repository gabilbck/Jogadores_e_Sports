CREATE TABLE jogador (
      id INT NOT NULL, 
      nome VARCHAR(100), 
      salario DOUBLE NOT NULL, 
      experiencia INT NOT NULL, 
      categoria INT, 
      PRIMARY KEY(idP), 
      FOREIGN KEY (categoria) REFERENCES tipo_jogo(id));

CREATE TABLE tipo_jogo (
      id INT NOT NULL, 
      categoria VARCHAR(2), 
      descricao VARCHAR(100), 
      PRIMARY KEY(id));
