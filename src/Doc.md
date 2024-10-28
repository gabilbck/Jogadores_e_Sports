## Por cima: 

**Main** -> classe executora, onde o usuário pode acessa as funcionalidades do CRUD de jogadores, categorias e equipes
- Jogadores
  - Adicionar, Atualizar, Deletar, Procurar, Listar, Salário
- Categorias
  - Listar
- Equipes
  - Listar

**Interfaces** -> aonde são definidos os métodos para manipulação de dados do jogador, categoria e equipes

**Funcs** -> "complemento" para a interface, onde os códigos, lógicas e sqls foram desenvolvidos

**Classes especificas**
  - Jogador
    - Veterano e Treinee herdam Jogador
  - Categoria
    - Solo e Grupo herdam Categoria
  - Equipes
