package dominio_laiz_e_gabrieli;

public class Equipe {
	private int id;
	private String nome;
	private int qtJogadores;
	private int categoria;
	
	public Equipe(int id, String nome, int qtJogadores, int categoria) {
		this.id = id;
		this.nome = nome;
		this.qtJogadores = qtJogadores;
		this.categoria = categoria;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getQtJogadores() {
		return qtJogadores;
	}
	public int getCategoria() {
		return categoria;
	}
}
