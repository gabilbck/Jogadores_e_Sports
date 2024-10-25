package dominio_laiz_e_gabrieli;

public class Equipe {
	private static final int NULL = 0;
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
		if(qtJogadores <= 1) {
			System.out.println("É necessário pelo menos 2 jogadores para formar uma equipe");
			qtJogadores = NULL;
		}
		return qtJogadores;
	}
	public int getCategoria() {
		return categoria;
	}
}
