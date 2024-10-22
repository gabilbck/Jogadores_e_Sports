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
![image](https://github.com/user-attachments/assets/cdca6b9d-ba09-416b-882c-0d480bb4e129)

### Coamandos para criação/visualização do BDD
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
create table jogador(id_jogador int not null, nome_jogador varchar(100) not null, salario_jogador double not null,  experiencia_jogador int not null, primary key(id_jogador));
```
```
desc jogador;
```

