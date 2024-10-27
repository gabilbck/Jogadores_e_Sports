package dominio_laiz_e_gabrieli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogadores_e_sports", "root", "");
            JogadorFuncs jogadorFuncs = new JogadorFuncs(conn);
            CategoriaFuncs categoriaFuncs = new CategoriaFuncs(conn);
            EquipeFuncs equipeFuncs = new EquipeFuncs(conn);

            boolean running = true;
            while (running) {
                System.out.println("Escolha uma opção: ");
                System.out.println("1. Adicionar Jogador");
                System.out.println("2. Buscar Jogador");
                System.out.println("3. Atualizar Jogador");
                System.out.println("4. Deletar Jogador");
                System.out.println("5. Listar Jogadores");
                System.out.println("6. Listar Categorias");
                System.out.println("7. Listar Equipes");
                System.out.println("8. Sair");

                //Variáveis de jogador
                int id;
                String nome;
                double salario;
                int experiencia;
                int categoriaId;
                int equipeId;
                
                int opcao = scanner.nextInt();
                scanner.nextLine(); // para limpar o buffer do teclado... e não dar aqueles erros igual na aula do manfred
                switch (opcao) {
	                case 1:    
	                    id = 0;
	                    System.out.print("Digite o nome: ");
	                    nome = scanner.nextLine();
	                    System.out.print("Digite o salário: ");
	                    salario = scanner.nextDouble();
	                    System.out.print("Digite os anos de treino: ");
	                    experiencia = scanner.nextInt();
	                    System.out.print("Digite a categoria (1 para solo e 2 para grupo): ");
	                    categoriaId = scanner.nextInt();
	                    
	                    if (categoriaId == 1) {
	                        equipeId = 1;
	                    } else {
	                        List<Equipe> equipes = equipeFuncs.listEquipes();
	                        System.out.println("Equipes disponíveis:");
	                        for (Equipe eq : equipes) {
	                            System.out.println("ID " + eq.getId() + " | Nome: " + eq.getNome());
	                        }
	                        
	                        boolean equipeValida = false;
	                        do {
	                            System.out.print("Digite o ID da equipe: ");
	                            equipeId = scanner.nextInt();
	
	                            // Verifica se o ID de equipe existe
	                            for (Equipe eq : equipes) {
	                                if (eq.getId() == equipeId) {
	                                    equipeValida = true;
	                                    break;
	                                }
	                            }
	                            
	                            if (!equipeValida) {
	                                System.out.println("ID de equipe inválido. Por favor, escolha um ID válido.");
	                            } else if (equipeId == 1) {
	                                System.out.println("ID de equipe inválido. Equipe ID 1 é reservada para categoria solo.");
	                                equipeValida = false;
	                            }
	                        } while (!equipeValida);
	                    }
	                    
	                    Jogador jogador;
	                    if (experiencia >= 5) {
	                        jogador = new JogadorVeterano(id, nome, salario, experiencia, equipeId, categoriaId);
	                    } else {
	                        jogador = new JogadorTrainee(id, nome, salario, experiencia, equipeId, categoriaId);
	                    }
	                    
	                    try {
	                        jogadorFuncs.addJogador(jogador);
	                        System.out.println("Jogador adicionado com sucesso.");
	                    } catch (Exception e) {
	                        System.out.println("Erro ao adicionar jogador: " + e.getMessage());
	                        e.printStackTrace();
	                    }
	                    break;

	                case 2:
	                    try {
	                        System.out.print("Digite o id do jogador: ");
	                        id = scanner.nextInt();
	                        
	                        // Tenta buscar o jogador pelo ID
	                        Jogador jogadorBuscado = jogadorFuncs.searchJogador(id);
	                        if (jogadorBuscado != null) {
	                            System.out.println("Jogador: " + jogadorBuscado.getNome() + " | Salario: " + jogadorBuscado.getSalario() + " | Experiencia: " + jogadorBuscado.getExperiencia() +  " | Equipe " + jogadorBuscado.getEquipe() + " | Categoria: " + jogadorBuscado.getCategoria());
	                        } else {
	                            System.out.println("Jogador não encontrado.");
	                        }
	                    } catch (Exception e) {
	                        // Captura possíveis exceções, incluindo entradas inválidas
	                        System.out.println("Jogador não encontrado.");
	                        scanner.nextLine(); // Limpa o buffer do scanner para evitar erros em futuras leituras
	                    }
	                    break;

	                case 3:
	                    System.out.println("(Para verificar id, selecione 'listar jogadores')");
	                    System.out.print("Digite o id do jogador para atualizar (ou 0 para ir ao menu): ");
	                    id = scanner.nextInt();
	                    if (id == 0) {
	                        break;
	                    } else {
	                        scanner.nextLine();
	                        Jogador jogadorExistente = jogadorFuncs.searchJogador(id);
	                        if (jogadorExistente != null) {
	                            System.out.print("Digite o novo nome (ou pressione 'Enter' para manter o nome atual: " + jogadorExistente.getNome() + "): ");
	                            nome = scanner.nextLine();
	                            if (nome.isEmpty()) {
	                                nome = jogadorExistente.getNome();
	                            }

	                            System.out.print("Digite o novo salário (ou pressione 'Enter' para manter o salário atual: " + jogadorExistente.getSalario() + "): ");
	                            String salarioInput = scanner.nextLine();
	                            if (salarioInput.isEmpty()) {
	                                salario = jogadorExistente.getSalario();
	                            } else {
	                                salario = Double.parseDouble(salarioInput);
	                            }

	                            System.out.print("Digite os novos anos de treino (ou pressione 'Enter' para manter o valor atual: " + jogadorExistente.getExperiencia() + "): ");
	                            String experienciaInput = scanner.nextLine();
	                            if (experienciaInput.isEmpty()) {
	                                experiencia = jogadorExistente.getExperiencia();
	                            } else {
	                                experiencia = Integer.parseInt(experienciaInput);
	                                if (experiencia < jogadorExistente.getExperiencia()) {
	                                    System.out.println("Anos de treino não podem ser diminuídos. Mantendo o valor atual: " + jogadorExistente.getExperiencia());
	                                    experiencia = jogadorExistente.getExperiencia();
	                                }
	                            }

	                            System.out.print("Digite a nova categoria (1 para solo e 2 para grupo, ou pressione Enter para manter a categoria atual: " + jogadorExistente.getCategoria() + "): ");
	                            String categoriaInput = scanner.nextLine();
	                            if (categoriaInput.isEmpty()) {
	                                categoriaId = jogadorExistente.getCategoria();
	                                equipeId = jogadorExistente.getEquipe();
	                            } else {
	                                categoriaId = Integer.parseInt(categoriaInput);
	                                if (categoriaId == 1) {
	                                    equipeId = 1;
	                                } else if (categoriaId == 2) {
	                                    System.out.print("Digite o ID da equipe (diferente de 1, ou pressione Enter para manter a equipe atual: " + jogadorExistente.getEquipe() + "): ");
	                                    String equipeInput = scanner.nextLine();
	                                    if (equipeInput.isEmpty()) {
	                                        equipeId = jogadorExistente.getEquipe();
	                                    } else {
	                                        equipeId = Integer.parseInt(equipeInput);
	                                        if (equipeId == 1) {
	                                            System.out.println("Erro: Equipe ID 1 é reservado para categoria solo. Mantendo equipe atual: " + jogadorExistente.getEquipe());
	                                            equipeId = jogadorExistente.getEquipe();
	                                        }
	                                    }
	                                } else {
	                                    System.out.println("Categoria inválida. Mantendo a categoria atual: " + jogadorExistente.getCategoria());
	                                    categoriaId = jogadorExistente.getCategoria();
	                                    equipeId = jogadorExistente.getEquipe();
	                                }
	                            }

	                            Jogador jogadorAtualizado;
	                            if (experiencia >= 5) {
	                                jogadorAtualizado = new JogadorVeterano(id, nome, salario, experiencia, equipeId, categoriaId);
	                            } else {
	                                jogadorAtualizado = new JogadorTrainee(id, nome, salario, experiencia, equipeId, categoriaId);
	                            }
	                            jogadorFuncs.uptJogador(jogadorAtualizado);
	                            System.out.println("Jogador atualizado com sucesso.");
	                        } else {
	                            System.out.println("Jogador não encontrado.");
	                        }
	                        break;
	                    }

                        
                    case 4:
                        System.out.print("Digite o id do jogador para deletar: ");
                        id = scanner.nextInt();
                        jogadorFuncs.delJogador(id);
                        System.out.println("Jogador deletado com sucesso.");
                        break;

                    case 5:
                        List<Jogador> jogadores = jogadorFuncs.listJogadores();
                        for (Jogador j : jogadores) {
                            System.out.println(j.getId() + " | Nome: "+ j.getNome() + " | Salário: " + j.getSalario() + " | Categoria: " + j.getCategoria() + " | Equipe: " + j.getEquipe());
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
                    	List<Equipe> equipes = equipeFuncs.listEquipes();
                        for (Equipe eq : equipes) {
                            System.out.println("ID " + eq.getId() + " | Nome: " + eq.getNome() + " | " + eq.getQtJogadores() + " jogadores.");
                        }
                        break;
                        
                    case 8:
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
