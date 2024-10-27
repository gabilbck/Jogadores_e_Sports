package dominio_laiz_e_gabrieli;

public class Equipe {
	private int id;
	private String nome;
	private int categoria;
	
	public Equipe(int id, String nome, int categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getCategoria() {
		return categoria;
	}
}
