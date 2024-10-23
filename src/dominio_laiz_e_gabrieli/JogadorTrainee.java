package dominio_laiz_e_gabrieli;

public class JogadorTrainee extends Jogador {
    public JogadorTrainee(int idJogador, String nome, double salario, int experiencia, int categoria) {
        super(idJogador, nome, salario, experiencia, categoria);
    }

    @Override
    public double calcularSalario() {
        return getSalario();  // Salário fixo
    }
}
