package dominio_laiz_e_gabrieli;

public class JogadorTrainee extends Jogador {
    private double salarioAtualizado;
    
    public JogadorTrainee(int id, String nome, double salario, int experiencia, int equipe, int categoria) {
        super(id, nome, salario, experiencia, equipe, categoria);
        this.salarioAtualizado = salarioAtualizado;
    }

    @Override
    public double calcularSalario() {
        return getSalario();  // Salário fixo
    }
}
