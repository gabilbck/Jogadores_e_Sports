# Jogadores_e_Sports

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

