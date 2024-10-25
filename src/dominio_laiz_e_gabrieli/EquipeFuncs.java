package dominio_laiz_e_gabrieli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipeFuncs implements EquipeInterface{
	private Connection conn;

    public EquipeFuncs(Connection conn) {
        this.conn = conn;
    }

	@Override
	public void addEquipe(Equipe equipe) throws Exception {
		String sql = "INSERT INTO equipe (nome, qt_jogadores, categoria) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setDouble(2, equipe.getQtJogadores());
            stmt.setInt(3, equipe.getCategoria());
            stmt.executeUpdate();
            System.out.println("Equipe cadastrada com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar equipe: " + ex.getMessage());
        }
    }

	@Override
	public Equipe searchEquipe(int id) throws Exception {
		String sql = "SELECT * FROM equipe WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                int qtJogadores = rs.getInt("qt_jogadores");
                int categoria = rs.getInt("categoria_id");
                return new Equipe(id, nome, qtJogadores, categoria);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar equipe: " + ex.getMessage());
        }
        return null;
	}

	@Override
	public void uptEquipe(Equipe equipe) throws Exception {
		String sql = "UPDATE equipe SET nome = ?, qt_jogadores = ?, categoria_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setDouble(2, equipe.getQtJogadores());
            stmt.setInt(3, equipe.getCategoria());
            stmt.executeUpdate();
            System.out.println("Equipe atualizada com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar equipe: " + ex.getMessage());
        }
	}

	@Override
	public void delEquipe(int id) throws Exception {
		String sql = "DELETE FROM equipe WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Equipe removida com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover equipe: " + ex.getMessage());
        }
	}

	@Override
	public List<Equipe> listEquipes() throws Exception {
		List<Equipe> listaEquipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int qtJogadores = rs.getInt("qt_jogadores");
                int categoria = rs.getInt("categoria_id");

                Equipe equipe;
                if (qtJogadores > 1) {
                    equipe = new Equipe(id, nome, qtJogadores, categoria);
                    listaEquipes.add(equipe);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar equipes: " + ex.getMessage());
        }
        return listaEquipes;
	}
}
