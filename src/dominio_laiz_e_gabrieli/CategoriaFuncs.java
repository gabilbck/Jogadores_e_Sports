package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaFuncs implements CategoriaInterface {
    private Connection conn;

    public CategoriaFuncs(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Categoria> listCategorias() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Categoria categoria;
                if (id == 1) {
                    categoria = new CategoriaSolo(id, nome, descricao);
                } else {
                    categoria = new CategoriaGrupo(id, nome, descricao);
                }
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar categorias: " + ex.getMessage());
            throw new Exception("Erro ao listar categorias", ex);
        }
        return categorias;
    }
}
