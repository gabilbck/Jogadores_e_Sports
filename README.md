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

### Bnaco de Dados (Única tabela):
![image](https://github.com/user-attachments/assets/cdca6b9d-ba09-416b-882c-0d480bb4e129)

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
CREATE TABLE tipo_jogo (
      id INT NOT NULL AUTO_INCREMENT, 
      categoria VARCHAR(2), 
      descricao VARCHAR(100), 
      PRIMARY KEY(id));

CREATE TABLE jogador (
      id INT NOT NULL AUTO_INCREMENT, 
      nome VARCHAR(100), 
      salario DOUBLE NOT NULL, 
      experiencia INT NOT NULL, 
      categoria INT, 
      PRIMARY KEY(idP), 
      FOREIGN KEY (categoria) REFERENCES tipo_jogo(id));
```
```
desc jogador;
```

