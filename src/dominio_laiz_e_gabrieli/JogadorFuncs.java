package dominio_laiz_e_gabrieli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorFuncs implements JogadorInterface {
    private Connection conn;

    public JogadorFuncs(Connection conn) {
        this.conn = conn;
    }

    @Override //Cadastrar
    public void addJogador(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO jogador (id, nome, salario, experiencia, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jogador.getId());
            stmt.setString(2, jogador.getNome());
            stmt.setDouble(3, jogador.calcularSalario());
            stmt.setInt(4, jogador.getExperiencia());
            stmt.setInt(5, jogador.getCategoria());
            stmt.executeUpdate();
        }
    }

    @Override //Procurar
    public Jogador searchJogador(int id) throws SQLException {
        String sql = "SELECT * FROM jogador WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                int categoria = rs.getInt("categoria");
                if (experiencia >= 5) {
                    return new JogadorVeterano(id, nome, salario, experiencia, categoria);
                } else {
                    return new JogadorTrainee(id, nome, salario, experiencia, categoria);
                }
            }
            return null;
        }
    }

    @Override //Atualizar
    public void uptJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogador SET nome = ?, salario = ?, experiencia = ?, categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.calcularSalario());
            stmt.setInt(3, jogador.getExperiencia());
            stmt.setInt(4, jogador.getCategoria());
            stmt.setInt(5, jogador.getId());
            stmt.executeUpdate();
        }
    }

    @Override //Remover
    public void delJogador(int idJogador) throws SQLException {
        String sql = "DELETE FROM jogador WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJogador);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Jogador> listJogadores() throws SQLException {
        List<Jogador> listaJogadores = new ArrayList<>(); 
        String sql = "SELECT * FROM jogador";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idJogador = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                int experiencia = rs.getInt("experiencia");
                int categoria = rs.getInt("categoria");
                Jogador jogador;  // Variável para instanciar o jogador
                if (experiencia >= 5) {
                    jogador = new JogadorVeterano(idJogador, nome, salario, experiencia, categoria);
                } else {
                    jogador = new JogadorTrainee(idJogador, nome, salario, experiencia, categoria);
                }
                listaJogadores.add(jogador);
            }
        }
        return listaJogadores;
    }
}
