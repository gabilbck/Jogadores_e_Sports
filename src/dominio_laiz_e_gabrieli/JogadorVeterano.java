package dominio_laiz_e_gabrieli;

public class JogadorVeterano extends Jogador {
    public JogadorVeterano(int idJogador, String nomeJogador, double salarioJogador, int experienciaJogador) {
        super(idJogador, nomeJogador, salarioJogador, experienciaJogador);
    }

    @Override
    public double calcularSalario() {
        return getSalarioJogador() * 1.10;  // 10% de aumento
    }
}