package dominio_laiz_e_gabrieli;

public abstract class Jogador {
	private int id;
	private String nome;
	private double salarioBruto;
	private double salarioTotalRecebido;
	private int experiencia;
	private int equipe;
	private int categoria;

    	// Setters
	public Jogador(int id, String nome, double salarioBruto, double salarioTotalRecebido, int experiencia, int equipe, int categoria) {
		this.id = id;
		this.nome = nome;
		this.salarioBruto = salarioBruto;
		this.salarioTotalRecebido = salarioTotalRecebido;
		this.experiencia = experiencia;
		this.equipe = equipe;
		this.categoria = categoria;
	}

    	// Getters
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public double getSalarioBruto() {
		return salarioBruto;
	}
	public double getSalarioTotalRecebido() {
		return salarioTotalRecebido;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public int getEquipe() {
		return equipe;
	}
	public int getCategoria() {
		return categoria;
	}
}
