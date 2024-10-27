# Jogadores_e_Sports

<a href="https://github.com/gabilbck" target="_blank">Gabrieli Eduarda Lembeck</a>
<br>
<a href="https://github.com/MegamiAy" target="_blank">Laiz Bordinhão Detros</a>

### Problema:
<p>
  Desenvolva operações CRUD para instanciar objetos jogadores 
de e-sportes em um SGBD relacional: código e nome como 
atributos desses objetos. Há jogadores veteranos com 5 anos 
ou mais de tempo de treino, assim como há jogadores trainees
com menos 5 anos de treino. Os jogadores veteranos recebem
10% a mais de salário do que os trainees (salário fixo). 
Para esse desenvolvimento explore Interface e tratamento de 
exceção, além da implementação das classes utilizando padrões
POO em Java e a classe Scanner
</p>

### Comandos para criação/visualização do BDD:
```
mysql -u root
```
```
show databases;
```
```
create database jogadores_e_sports;
```
```
use jogadores_e_sports;
```
```
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
```
```
show tables;
```
```
desc jogador;

desc categoria;

desc equipe;
```

### Registros de Teste:
```
INSERT INTO categoria (id, nome, descricao)
VALUES ("1", "Solo", "Nesta categoria os jogadores jogam de forma individual. Times sao proibidos.");

INSERT INTO categoria (id, nome, descricao)
VALUES ("2", "Grupo", "Nesta categoria os jogadores jogam acompanhados.");

INSERT INTO equipe (id, nome, categoria_id)
VALUES ("1", "Sem equipe", "1");

INSERT INTO equipe (id, nome, categoria_id)
VALUES ("2", "Diamond", "2");

INSERT INTO equipe (id, nome, categoria_id)
VALUES ("3", "Stars", "2");

INSERT INTO jogador(id, nome, salario_bruto, salario_total_recebido, experiencia, equipe_id, categoria_id)
VALUES ("1", "Joao", "3500.00", "0", "2", "2", "2");

INSERT INTO jogador(id, nome, salario_bruto, salario_total_recebido, experiencia, equipe_id, categoria_id)
VALUES ("2", "Lucas", "9500.00", "0", "6", 1, "1");
```
