package dominio_laiz_e_gabrieli;

import java.util.List;

public interface JogadorDAO {
    void adicionarJogador(Jogador jogador) throws Exception;
    Jogador buscarJogador(int idJogador) throws Exception;
    void atualizarJogador(Jogador jogador) throws Exception;
    void deletarJogador(int idJogador) throws Exception;
    List<Jogador> listarJogadores() throws Exception;
}