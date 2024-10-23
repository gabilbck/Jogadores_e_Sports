package dominio_laiz_e_gabrieli;

public abstract class Categoria {
	    private int idCategoria;
	    private String categoria;
	    private String descricao;

    	// Setters
  	  public Categoria(int idCategoria, String categoria, String descricao) {
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
  	  
	  // Metodos
}
