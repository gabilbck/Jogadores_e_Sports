# Jogadores_e_Sports

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

### Bnaco de Dados:

<strong>Diagrama (Planejamento)</strong><br>
![image](https://github.com/user-attachments/assets/b54acfd5-9323-40c0-b56b-237b9fc56e12)

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
    qt_Jogadores INT,
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
INSERT INTO categoria (id, categoria, descricao)
VALUES ("1", "Solo", "Nesta categoria os jogadores jogam de forma individual. Times sao proibidos.");

INSERT INTO categoria (id, categoria, descricao)
VALUES ("2", "Grupo", "Nesta categoria os jogadores jogam acompanhados.");

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES ("", "Diamond", "5", "2");

INSERT INTO equipe (id, nome, qt_jogadores, categoria_id)
VALUES ("", "Stars", "6", "2");

INSERT INTO jogador(id, nome, salario, experiencia, equipe_id, categoria_id)
VALUES ("", "Joao", "3500.00", "2", "1", "2");

INSERT INTO jogador(id, nome, salario, experiencia, equipe_id, categoria_id)
VALUES ("", "Lucas", "9500.00", "6", NULL, "1");
```
