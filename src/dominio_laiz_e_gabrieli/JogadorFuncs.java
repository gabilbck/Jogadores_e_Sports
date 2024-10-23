package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class JogadorFuncs implements JogadorInterface {
    private Connection conn;

    public JogadorFuncs(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addJogador(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO jogador (idJogador, nome, salario, experiencia, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jogador.getIdJogador());
            stmt.setString(2, jogador.getNome());
            stmt.setDouble(3, jogador.calcularSalario());
            stmt.setInt(4, jogador.getExperiencia());
            stmt.executeUpdate();
        }
    }

    @Override
    public Jogador searchJogador(int idJogador) throws SQLException {
        String sql = "SELECT * FROM jogadores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJogador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                if (experiencia >= 5) {
                    return new JogadorVeterano(idJogador, nome, salario, experiencia, categoria);
                } else {
                    return new JogadorTrainee(idJogador, nome, salario, experiencia, categoria);
                }
            }
            return null;
        }
    }

    @Override
    public void uptJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogador SET nome = ?, salario = ?, experiencia = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.calcularSalario());
            stmt.setInt(3, jogador.getExperiencia());
            stmt.setInt(4, jogador.getIdJogador());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delJogador(int idJogador) throws SQLException {
        String sql = "DELETE FROM jogadores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJogador);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Jogador> listJogadores() throws SQLException {
        List<Jogador> jogador = new ArrayList<>();
        String sql = "SELECT * FROM jogador";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idJogador = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                Jogador jogador;
                if (experiencia >= 5) {
                    jogador = new JogadorVeterano(idJogador, nome, salario, experiencia, categoria);
                } else {
                    jogador = new JogadorTrainee(idJogador, nome, salario, experiencia, categoria);
                }
                jogador.add(jogador);
            }
        }
        return jogador;
    }
}
