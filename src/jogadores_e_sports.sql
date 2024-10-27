CREATE TABLE categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE equipe (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    qt_jogadores INT,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE jogador (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    salario DOUBLE,
    experiencia INT,
    equipe_id INT,
    categoria_id INT,
    FOREIGN KEY (equipe_id) REFERENCES equipe(id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);


/*
criando as duas categorias:
id-> 1 - solo
id-> 2 - grupo
*/

INSERT INTO categoria (id, nome, descricao)
VALUES (1, "Solo", "Nesta categoria os jogadores jogam de forma individual. Times são proibidos."),
       (2, "Grupo", "Nesta categoria os jogadores jogam acompanhados.");

-- ou

INSERT INTO categoria (id, categoria, descricao)
VALUES ("1", "Solo", "Nesta categoria os jogadores jogam de forma individual. Times sao proibidos.");

INSERT INTO categoria (id, categoria, descricao)
VALUES ("2", "Grupo", "Nesta categoria os jogadores jogam acompanhados.");

/*
criando duas equipes base/exemplo:
*/

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES (1, "Sem Equipe", 0, 1) 
       (2, "Diamond", 5, 2),
       (3, "Stars", 6, 2);

-- ou

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES ("", "Sem Equipe", "0", "1");

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES ("", "Diamond", "5", "2");

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES ("", "Stars", "6", "2");

/*
criando dois jogadores base/exemplo:
treinee que joga na categoria de grupo
veterno que joga na categoria solo
*/
-- não é necessário passar um id, pois os campos de PK são Auto_increment

INSERT INTO jogador(id, nome, salario, experiencia, equipe_id, categoria_id)
VALUES (1, "Joao", 3550.00, 2, 2, 2),
       (2, "Maria", 9500.00, 6, 1, 1),
       (3, "Lucas", 4070.00, 4, 3, 2);

-- ou

INSERT INTO jogador(id, nome, salario, experiencia, equipe_id, categoria_id)
VALUES ("", "Joao", "3500.00", "2", "1", "2");

INSERT INTO jogador(id, nome, salario, experiencia, equipe_id, categoria_id)
VALUES ("", "Lucas", "9500.00", "6", NULL, "1");
