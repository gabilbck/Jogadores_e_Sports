package dominio_laiz_e_gabrieli;

public abstract class Jogador {
	    private int idCategoria;
	    private String categoria;
	    private String descricao;

    	// Setters
  	  public Jogador(int idCategoria, String categoria, String descricao) {
  		  this.idCategoria = idCategoria;
  		  this.categoria = categoria;
  		  this.descricao = descricao;
  	  }

    	// Getters
  	  public int getIdCategoria() {
  		  return idCategoria;
  	  }
  	  public String getCategoria() {
  		  return categoria;
  	  }
  	  public String getDesc() {
  		  return descricao;
  	  }
  	
  	  public int getCategoria() {
  		  return categoria;
  	  }
	
	  // Metodos
	  //public abstract double calcular();
}
