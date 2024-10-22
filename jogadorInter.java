public interface JogadorInter{
    void addJogador(Jogador jogador) throws Exception;      // add  - adicionar jogador
    Jogador searchJogador(int codigo) throws Exception;     // search - pesquisa jogador
    void uptJogador(Jogador jogador) throws Exception;      // update - atualizar jogador
    void delJogador(int codigo) throws Exception;           // delete - deletar jogador
    List<Jogador> listJogadores() throws Exception;         // list - lista os jogadores
}
