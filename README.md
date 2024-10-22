# Jogadores_e_Sports

### Bnaco de Dados:
![image](https://github.com/user-attachments/assets/ce982198-46a4-4b64-bd4f-43ecaf27b0d2)

### Coamandos para criação/visualização do BDD
```mysql -u root```
```show databases;```
```create database jogadores_e_sports;```
```use jogadores_e_sports;```
```
create table jogador(id_jogador int not null, 
nome_jogador varchar(100) not null,
salario_jogador double not null, 
experiencia_jogador int not null, 
primary key(id_jogador));
```
```desc jogador```
