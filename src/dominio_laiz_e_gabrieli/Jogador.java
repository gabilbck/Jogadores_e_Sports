package dominio_laiz_e_gabrieli;

public abstract class Jogador {
    private int idJogador;
    private String nomeJogador;
    private double salarioJogador;
    private int experienciaJogador;

    // Setters
    public Jogador(int idJogador, String nomeJogador, double salarioJogador, int experienciaJogador) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.salarioJogador = salarioJogador;
        this.experienciaJogador = experienciaJogador;
    }

    // Getters
    public int getIdJogador() {
		return idJogador;
	}
	public String getNomeJogador() {
		return nomeJogador;
	}
	public double getSalarioJogador() {
		return salarioJogador;
	}
	public int getExperienciaJogador() {
		return experienciaJogador;
	}

	// Metodos
    public abstract double calcularSalario();
}