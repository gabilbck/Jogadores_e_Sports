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

<strong>jogador</strong><br>
![image](https://github.com/user-attachments/assets/bcd14ba5-f05a-4ae3-b075-fb1b55cfe848)

<strong>tipo_jogo</strong><br>
![image](https://github.com/user-attachments/assets/2ae7fb15-53a5-4390-b983-7bb7d0cc0395)

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
CREATE TABLE categoria_jogo (
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
      PRIMARY KEY(id), 
      FOREIGN KEY (categoria) REFERENCES categoria_jogo(id));
```
```
desc jogador;
```

