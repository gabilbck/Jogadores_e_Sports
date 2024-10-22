package dominio_laiz_e_gabrieli;

public class JogadorTrainee extends Jogador {
    public JogadorTrainee(int idJogador, String nomeJogador, double salarioJogador, int experienciaJogador) {
        super(idJogador, nomeJogador, salarioJogador, experienciaJogador);
    }

    @Override
    public double calcularSalario() {
        return getSalarioJogador();  // Salário fixo
    }
}