package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorFuncs implements JogadorInterface {
    private Connection conn;

    public JogadorFuncs(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addJogador(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO jogadores (idJogador, nomeJogador, salarioJogador, experienciaJogador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jogador.getidJogador());
            stmt.setString(2, jogador.getnomeJogador());
            stmt.setDouble(3, jogador.calcularsalarioJogador());
            stmt.setInt(4, jogador.getexperienciaJogador());
            stmt.executeUpdate();
        }
    }

    @Override
    public Jogador searchJogador(int idJogador) throws SQLException {
        String sql = "SELECT * FROM jogadores WHERE idJogador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJogador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nomeJogador = rs.getString("nomeJogador");
                double salarioJogador = rs.getDouble("salarioJogador");
                int experienciaJogador = rs.getInt("experienciaJogador");
                if (experienciaJogador >= 5) {
                    return new JogadorVeterano(idJogador, nomeJogador, salarioJogador, experienciaJogador);
                } else {
                    return new JogadorTrainee(idJogador, nomeJogador, salarioJogador, experienciaJogador);
                }
            }
            return null;
        }
    }

    @Override
    public void uptJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogadores SET nomeJogador = ?, salarioJogador = ?, experienciaJogador = ? WHERE idJogador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getnomeJogador());
            stmt.setDouble(2, jogador.calcularsalarioJogador());
            stmt.setInt(3, jogador.getexperienciaJogador());
            stmt.setInt(4, jogador.getidJogador());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delJogador(int idJogador) throws SQLException {
        String sql = "DELETE FROM jogadores WHERE idJogador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJogador);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Jogador> listJogadores() throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogadores";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idJogador = rs.getInt("id_jogador");
                String nomeJogador = rs.getString("nome_jogador");
                double salarioJogador = rs.getDouble("salario_jogador");
                int experienciaJogador = rs.getInt("experiencia_jogador");
                Jogador jogador;
                if (experienciaJogador >= 5) {
                    jogador = new JogadorVeterano(idJogador, nomeJogador, salarioJogador, experienciaJogador);
                } else {
                    jogador = new JogadorTrainee(idJogador, nomeJogador, salarioJogador, experienciaJogador);
                }
                jogadores.add(jogador);
            }
        }
        return jogadores;
    }
}
