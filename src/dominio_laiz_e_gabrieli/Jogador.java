package dominio_laiz_e_gabrieli;

public abstract class Jogador {
	private int idJogador;
	private String nome;
	private double salario;
	private int experiencia;
	private int categoria;

    	// Setters
	public Jogador(int idJogador, String nome, double salario, int experiencia, int categoria) {
		this.idJogador = idJogador;
		this.nome = nome;
		this.salario = salario;
		this.experiencia = experiencia;
		this.categoria = categoria;
	}

    	// Getters
	public int getIdJogador() {
		return idJogador;
	}
	public String getNome() {
		return nome;
	}
	public double getSalario() {
		return salario;
	}
	public int getExperiencia() {
		return experiencia;
	}
	
	public int getCategoria() {
		return categoria;
	}
	
	// Metodos
	public abstract double calcularSalario();
}
