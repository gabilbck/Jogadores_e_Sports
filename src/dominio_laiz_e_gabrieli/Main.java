package dominio_laiz_e_gabrieli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogadores_e_sports", "root", "");
            JogadorFuncs jogadorFuncs = new JogadorFuncs(conn);
            CategoriaFuncs categoriaFuncs = new CategoriaFuncs(conn);

            boolean running = true;
            while (running) {
                System.out.println("Escolha uma opção: ");
                System.out.println("1. Adicionar Jogador");
                System.out.println("2. Buscar Jogador");
                System.out.println("3. Atualizar Jogador");
                System.out.println("4. Deletar Jogador");
                System.out.println("5. Listar Jogadores");
                System.out.println("6. Listar Categorias");
                System.out.println("7. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // para limpar o buffer do teclado... e não dar aqueles erros igual na aula do manfred
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o código: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o salário: ");
                        double salario = scanner.nextDouble();
                        System.out.print("Digite os anos de treino: ");
                        int anosTreino = scanner.nextInt();
                        System.out.print("Digite o ID da equipe: ");
                        int equipeId = scanner.nextInt();
                        System.out.print("Digite a categoria (1 para solo e 2 para grupo): ");
                        int categoriaId = scanner.nextInt();

                        Jogador jogador;
                        if (anosTreino >= 5) {
                            jogador = new JogadorVeterano(codigo, nome, salario, anosTreino, equipeId, categoriaId);
                        } else {
                            jogador = new JogadorTrainee(codigo, nome, salario, anosTreino, equipeId, categoriaId);
                        }
                        jogadorFuncs.addJogador(jogador);
                        System.out.println("Jogador adicionado com sucesso.");
                        break;

                    case 2:
                        System.out.print("Digite o código do jogador: ");
                        int codigoBusca = scanner.nextInt();
                        Jogador jogadorBuscado = jogadorFuncs.searchJogador(codigoBusca);
                        if (jogadorBuscado != null) {
                            System.out.println("Jogador encontrado: " + jogadorBuscado.getNome());
                        } else {
                            System.out.println("Jogador não encontrado.");
                        }
                        break;

                    case 3:
                        System.out.print("Digite o código do jogador para atualizar: ");
                        int codigoAtualizar = scanner.nextInt();
                        scanner.nextLine(); 
                        Jogador jogadorExistente = jogadorFuncs.searchJogador(codigoAtualizar);
                        if (jogadorExistente != null) {
                            System.out.print("Digite o novo nome: ");
                            nome = scanner.nextLine();
                            scanner.nextLine(); 
                            System.out.print("Digite o novo salário: ");
                            salario = scanner.nextDouble();
                            System.out.print("Digite os novos anos de treino: ");
                            anosTreino = scanner.nextInt();
                            System.out.print("Digite o novo ID da equipe: ");
                            equipeId = scanner.nextInt();
                            System.out.print("Digite a nova categoria (1 para solo e 2 para grupo): ");
                            categoriaId = scanner.nextInt();

                            Jogador jogadorAtualizado;
                            if (anosTreino >= 5) {
                                jogadorAtualizado = new JogadorVeterano(codigoAtualizar, nome, salario, anosTreino, equipeId, categoriaId);
                            } else {
                                jogadorAtualizado = new JogadorTrainee(codigoAtualizar, nome, salario, anosTreino, equipeId, categoriaId);
                            }
                            jogadorFuncs.uptJogador(jogadorAtualizado);
                        } else {
                            System.out.println("Jogador não encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o código do jogador para deletar: ");
                        int codigoDeletar = scanner.nextInt();
                        jogadorFuncs.delJogador(codigoDeletar);
                        System.out.println("Jogador deletado com sucesso.");
                        break;

                    case 5:
                        List<Jogador> jogadores = jogadorFuncs.listJogadores();
                        for (Jogador j : jogadores) {
                            System.out.println(j.getNome() + " - Salário: " + j.calcularSalario() + " - Categoria Jogada: " + j.getCategoria());
                        }
                        break;

                    case 6:
                    	try {
                    		List<Categoria> categorias = categoriaFuncs.listCategorias();
                            for (Categoria c : categorias) {
                                System.out.println(c.getNome() + " - Descrição: " + c.getDesc());
                            }
                    	} catch (Exception e) {
                    	    System.out.println("Erro: " + e.getMessage());
                    	    e.printStackTrace();
                    	}
                        break;
                        
                    case 7:
                        running = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
