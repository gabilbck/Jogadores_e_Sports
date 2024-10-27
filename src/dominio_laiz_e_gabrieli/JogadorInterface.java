package dominio_laiz_e_gabrieli;

import java.util.List;

public interface JogadorInterface{
    void addJogador(Jogador jogador) throws Exception;      // add  - adicionar jogador
    Jogador searchJogador(int id) throws Exception;     // search - pesquisa jogador
    void uptJogador(Jogador jogador) throws Exception;      // update - atualizar jogador
    void delJogador(int id) throws Exception;           // delete - deletar jogador
    List<Jogador> listJogadores() throws Exception;         // list - lista os jogadores
    public void receberSalario(int id, double salarioRecebido) throws Exception;
}
