package dominio_laiz_e_gabrieli;

public class JogadorVeterano extends Jogador {
	private double salarioAtualizado;
	
    public JogadorVeterano(int id, String nome, double salario, int experiencia, int equipe,int categoria) {
        super(id, nome, salario, experiencia, equipe, categoria);
        this.
    }

    @Override
    public double calcularSalario() {
        return getSalario() * 1.10d;  // 10% de aumento
    }
}
