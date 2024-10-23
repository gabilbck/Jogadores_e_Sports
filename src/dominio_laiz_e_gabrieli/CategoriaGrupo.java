package dominio_laiz_e_gabrieli;

public class CategoriaGrupo extends Categoria {
    private qt_integrantes;
  
    public CategoriaGrupo(int idCategoria, String categoria, String descricao) {
        super(idCategoria, categoria, descricao);
        this.qt_integrantes = qt_integrantes;
    }
}
