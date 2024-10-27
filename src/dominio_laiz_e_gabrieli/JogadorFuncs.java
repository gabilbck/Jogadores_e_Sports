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
        String sql = "INSERT INTO jogador (nome, salario_bruto, salario_total_recebido, experiencia, equipe_id, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.getSalarioBruto());
            stmt.setDouble(3, jogador.getSalarioTotalRecebido());
            stmt.setInt(4, jogador.getExperiencia());
            stmt.setInt(5, jogador.getEquipe());
            stmt.setInt(6, jogador.getCategoria());
            stmt.executeUpdate();
            System.out.println("Jogador cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar jogador: " + ex.getMessage());
            throw ex;
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
                double salarioBruto = rs.getDouble("salario_bruto");
                double salarioTotalRecebido = rs.getDouble("salario_total_recebido");
                int experiencia = rs.getInt("experiencia");
                int equipe = rs.getInt("equipe_id");
                int categoria = rs.getInt("categoria_id");
                if (experiencia >= 5) {
                    return new JogadorVeterano(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipe, categoria);
                } else {
                    return new JogadorTrainee(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipe, categoria);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar jogador: " + ex.getMessage());
            throw ex;
        }
        return null;
    }

    @Override // Atualizar
    public void uptJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE jogador SET nome = ?, salario_bruto = ?, salario_total_recebido = ?, experiencia = ?, equipe_id = ?, categoria_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setDouble(2, jogador.getSalarioBruto());
            stmt.setDouble(3, jogador.getSalarioTotalRecebido());
            stmt.setInt(4, jogador.getExperiencia());
            stmt.setInt(5, jogador.getEquipe());
            stmt.setInt(6, jogador.getCategoria());
            stmt.setInt(7, jogador.getId());
            stmt.executeUpdate();
    
            if (jogador.getExperiencia() >= 5) {
                delJogador(jogador.getId());
                addJogador(new JogadorVeterano(jogador.getId(), jogador.getNome(), jogador.getSalarioBruto(), jogador.getSalarioTotalRecebido(), jogador.getExperiencia(), jogador.getEquipe(), jogador.getCategoria()));
            } else {
                delJogador(jogador.getId());
                addJogador(new JogadorTrainee(jogador.getId(), jogador.getNome(), jogador.getSalarioBruto(), jogador.getSalarioTotalRecebido(), jogador.getExperiencia(), jogador.getEquipe(), jogador.getCategoria()));
            }
    
            System.out.println("Jogador atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar jogador: " + ex.getMessage());
            throw ex;
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
            throw ex;
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
                double salarioBruto = rs.getDouble("salario_bruto");
                double salarioTotalRecebido = rs.getDouble("salario_total_recebido");
                int experiencia = rs.getInt("experiencia");
                int equipe = rs.getInt("equipe_id");
                int categoria = rs.getInt("categoria_id");

                Jogador jogador;
                if (experiencia >= 5) {
                    jogador = new JogadorVeterano(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipe, categoria);
                } else {
                    jogador = new JogadorTrainee(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipe, categoria);
                }
                listaJogadores.add(jogador);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar jogadores: " + ex.getMessage());
            throw ex;
        }
        return listaJogadores;
    }
    
    @Override // Receber salário
    public void receberSalario(int id, double salarioRecebido) throws SQLException {
    	String selectSql = "SELECT experiencia FROM jogador WHERE id = ?";
        String updateSql = "UPDATE jogador SET salario_total_recebido = salario_total_recebido + ? WHERE id = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            
            // Verifica a experiência do jogador
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int experiencia = rs.getInt("experiencia");
                
                // Aplica um bônus de 10% ao salário se for veterano
                if (experiencia >= 5) {
                    salarioRecebido *= 1.10;
                }
            } else {
                System.out.println("Jogador com ID " + id + " não encontrado.");
                return;
            }

            // Atualiza o salário total recebido
            updateStmt.setDouble(1, salarioRecebido);
            updateStmt.setInt(2, id);
            int linhasAfetadas = updateStmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Salário atualizado no banco de dados com sucesso.");
            } else {
                System.out.println("Jogador com ID " + id + " não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar salário: " + ex.getMessage());
            throw ex;
        }
    }

}
