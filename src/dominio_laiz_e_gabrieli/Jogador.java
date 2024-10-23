package dominio_laiz_e_gabrieli;

public abstract class Jogador {
	private int id;
	private String nome;
	private double salario;
	private int experiencia;
	private int categoria;

    	// Setters
	public Jogador(int id, String nome, double salario, int experiencia, int categoria) {
		this.id = id;
		this.nome = nome;
		this.salario = salario;
		this.experiencia = experiencia;
		this.categoria = categoria;
	}

    	// Getters
	public int getId() {
		return id;
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
