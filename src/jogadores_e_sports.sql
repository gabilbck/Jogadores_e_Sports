CREATE TABLE categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE equipe (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE jogador (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    salario_bruto DOUBLE,
    salario_total_recebido DOUBLE,
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

/*
criando duas equipes base/exemplo:
*/

INSERT INTO equipe (id, nome, categoria_id)
VALUES (1, "Sem Equipe", 1),
       (2, "Diamond", 2),
       (3, "Stars", 2);

/*
criando dois jogadores base/exemplo:
treinee que joga na categoria de grupo
veterno que joga na categoria solo
*/
-- não é necessário passar um id, pois os campos de PK são Auto_increment

INSERT INTO jogador(id, nome, salario_bruto, salario_total_recebido, experiencia, equipe_id, categoria_id)
VALUES (1, "Joao", 3550.00, 0, 2, 2, 2),
       (2, "Maria", 9500.00, 0, 6, 1, 1),
       (3, "Lucas", 4070.00, 0, 4, 3, 2);
