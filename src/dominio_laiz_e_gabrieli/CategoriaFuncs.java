package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class CategoriaFuncs implements CategoriaInterface {
    private Connection conn;

    public CategoriaFuncs(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listCategorias() throws SQLException {
        List<Categoria> categoria = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idCategoria = rs.getInt("id");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                Categoria categoria;
                if (idCategoria == 1) {
                    categoria = new CategoriaSolo(idCategoria, categoria, descricao);
                } else {
                    categoria = new CategoriaGrupo(idCategoria, categoria, descricao);
                }
                categoria.add(categoria);
            }
        }
        return categoria;
    }
}
