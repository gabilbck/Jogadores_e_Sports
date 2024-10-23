package dominio_laiz_e_gabrieli;

public class JogadorVeterano extends Jogador {
    public JogadorVeterano(int idJogador, String nome, double salario, int experiencia, int categoria) {
        super(idJogador, nomer, salario, experiencia, categoria);
    }

    @Override
    public double calcularSalario() {
        return getSalario() * 1.60;  // 60% de aumento
    }
}
