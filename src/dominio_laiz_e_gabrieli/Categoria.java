package dominio_laiz_e_gabrieli;

public abstract class Categoria {
	    private int id;
	    private String nome;
	    private String descricao;

    	// Setters
  	  public Categoria(int id, String nome, String descricao) {
  		  this.id = id;
  		  this.nome = nome;
  		  this.descricao = descricao;
  	  }

    	// Getters
  	  public int getId() {
  		  return id;
  	  }
  	  public String getNome() {
  		  return nome;
  	  }
  	  public String getDesc() {
  		  return descricao;
  	  }
  	  
	  // Metodos
}
