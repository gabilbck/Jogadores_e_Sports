package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorFuncs implements JogadorInterface {
    private Connection conn;

    public JogadorFuncs(Connection conn) {
        this.conn = conn;
    }

    @Override // Cadastrar
    public void addJogador(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO jogador (nome, salario, experiencia, equipe_id, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.calcularSalario());
            stmt.setInt(3, jogador.getExperiencia());
            stmt.setInt(4, jogador.getEquipe());
            stmt.setInt(5, jogador.getCategoria());
            stmt.executeUpdate();
            System.out.println("Jogador cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar jogador: " + ex.getMessage());
        }
    }

    @Override // Procurar
    public Jogador searchJogador(int id) throws SQLException {
        String sql = "SELECT * FROM jogador WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                int equipe = rs.getInt("equipe_id");
                int categoria = rs.getInt("categoria_id");
                if (experiencia >= 5) {
                    return new JogadorVeterano(id, nome, salario, experiencia, equipe, categoria);
                } else {
                    return new JogadorTrainee(id, nome, salario, experiencia, equipe, categoria);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar jogador: " + ex.getMessage());
        }
        return null;
    }

    @Override // Atualizar
    public void uptJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogador SET nome = ?, salario = ?, experiencia = ?, equipe_id = ?, categoria_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.calcularSalario());
            stmt.setInt(3, jogador.getExperiencia());
            stmt.setInt(4, jogador.getEquipe());
            stmt.setInt(5, jogador.getCategoria());
            stmt.executeUpdate();
            System.out.println("Jogador atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar jogador: " + ex.getMessage());
        }
    }

    @Override // Remover
    public void delJogador(int id) throws SQLException {
        String sql = "DELETE FROM jogador WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Jogador removido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover jogador: " + ex.getMessage());
        }
    }

    @Override // Listar todos
    public List<Jogador> listJogadores() throws SQLException {
        List<Jogador> listaJogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogador";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                int equipe = rs.getInt("equipe_id");
                int categoria = rs.getInt("categoria_id");

                Jogador jogador;
                if (experiencia >= 5) {
                    jogador = new JogadorVeterano(id, nome, salario, experiencia, equipe, categoria);
                } else {
                    jogador = new JogadorTrainee(id, nome, salario, experiencia, equipe, categoria);
                }
                listaJogadores.add(jogador);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar jogadores: " + ex.getMessage());
        }
        return listaJogadores;
    }
}
