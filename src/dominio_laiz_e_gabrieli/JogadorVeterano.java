package dominio_laiz_e_gabrieli;

public class JogadorVeterano extends Jogador {
    public JogadorVeterano(int id, String nome, double salario, int experiencia, int categoria) {
        super(id, nome, salario, experiencia, categoria);
    }

    @Override
    public double calcularSalario() {
        return getSalario() * 1.60;  // 60% de aumento
    }
}
