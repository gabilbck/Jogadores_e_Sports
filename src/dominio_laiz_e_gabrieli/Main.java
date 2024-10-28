package dominio_laiz_e_gabrieli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
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
                System.out.println("8. Receber Salário");
                System.out.println("9. Sair");

                //Variáveis de jogador
                int id;
                String nome;
                double salarioBruto;
                double salarioTotalRecebido;
                int experiencia;
                int categoriaId;
                int equipeId;
                
                int opcao = scanner.nextInt();
                scanner.nextLine(); // para limpar o buffer do teclado... e não dar aqueles erros igual na aula do manfred
                switch (opcao) {   
                case 1:
                    id = 0;
                    salarioTotalRecebido = 0;
                    categoriaId = 0;
                    equipeId = 0;

                    System.out.print("Digite o nome: ");
                    nome = scanner.nextLine();

                    // Tratamento para salário bruto
                    while (true) {
                        try {
                            System.out.print("Digite o salário Bruto: ");
                            salarioBruto = scanner.nextDouble();
                            if (salarioBruto < 0) {
                                System.out.println("Erro: o salário bruto deve ser positivo.");
                            } else {
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, digite um valor numérico para o salário.");
                            scanner.nextLine(); // Limpa o buffer do scanner
                        }
                    }

                    // Tratamento para anos de experiência
                    while (true) {
                        try {
                            System.out.print("Digite os anos de treino: ");
                            experiencia = scanner.nextInt();
                            if (experiencia < 0) {
                                System.out.println("Erro: a experiência deve ser um número positivo.");
                            } else {
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número inteiro para os anos de treino.");
                            scanner.nextLine(); // Limpa o buffer do scanner
                        }
                    }

                    // Tratamento para categoria
                    boolean categoriaValida = false;
                    do {
                        try {
                            System.out.print("Digite a categoria (1 para solo e 2 para grupo): ");
                            categoriaId = scanner.nextInt();
                            if (categoriaId == 1 || categoriaId == 2) {
                                categoriaValida = true;
                            } else {
                                System.out.println("ID de categoria inválido. Por favor, escolha 1 para solo ou 2 para grupo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número inteiro para a categoria.");
                            scanner.nextLine(); // Limpa o buffer do scanner
                        }
                    } while (!categoriaValida);

                    // Tratamento para equipe (apenas para categoria grupo)
                    if (categoriaId == 2) {
                        List<Equipe> equipes = equipeFuncs.listEquipes();
                        System.out.println("Equipes disponíveis:");
                        for (Equipe eq : equipes) {
                            System.out.println("ID " + eq.getId() + " | Nome: " + eq.getNome());
                        }

                        boolean equipeValida = false;
                        do {
                            try {
                                System.out.print("Digite o ID da equipe: ");
                                equipeId = scanner.nextInt();
                                
                                for (Equipe eq : equipes) {
                                    if (eq.getId() == equipeId) {
                                        equipeValida = true;
                                        break;
                                    }
                                }
                                
                                if (!equipeValida) {
                                    System.out.println("ID de equipe inexistente. Por favor, escolha um ID válido.");
                                } else if (equipeId == 1) {
                                    System.out.println("ID de equipe inválido. Equipe ID 1 é reservada para categoria solo.");
                                    equipeValida = false;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida. Por favor, digite um número inteiro para o ID da equipe.");
                                scanner.nextLine(); // Limpa o buffer do scanner
                            }
                        } while (!equipeValida);
                    } else {
                        equipeId = 1; // ID para solo
                    }

                    Jogador jogador;
                    if (experiencia >= 5) {
                        jogador = new JogadorVeterano(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipeId, categoriaId);
                    } else {
                        jogador = new JogadorTrainee(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipeId, categoriaId);
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
	                            System.out.println("Jogador: " + jogadorBuscado.getNome() + " | Salario bruto: " + jogadorBuscado.getSalarioBruto() + " | Salario total recebido: " + jogadorBuscado.getSalarioTotalRecebido() + " | Experiencia: " + jogadorBuscado.getExperiencia() +  " | Equipe " + jogadorBuscado.getEquipe() + " | Categoria: " + jogadorBuscado.getCategoria());
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
	                    try {
	                        System.out.print("Digite o id do jogador para atualizar (ou 0 para ir ao menu): ");
	                        id = scanner.nextInt();
	                        if (id == 0) {
	                            break;
	                        }
	                        scanner.nextLine(); // Limpa o buffer

	                        Jogador jogadorExistente = jogadorFuncs.searchJogador(id);
	                        if (jogadorExistente != null) {
	                            System.out.print("Digite o novo nome (ou pressione 'Enter' para manter o nome atual: " + jogadorExistente.getNome() + "): ");
	                            nome = scanner.nextLine();
	                            if (nome.isEmpty()) {
	                                nome = jogadorExistente.getNome();
	                            }

	                            // Salário Bruto
	                            System.out.print("Digite o novo salário Bruto (ou pressione 'Enter' para manter o salário atual: " + jogadorExistente.getSalarioBruto() + "): ");
	                            String salarioBrutoInput = scanner.nextLine();
	                            if (salarioBrutoInput.isEmpty()) {
	                                salarioBruto = jogadorExistente.getSalarioBruto();
	                            } else {
	                                try {
	                                    salarioBruto = Double.parseDouble(salarioBrutoInput);
	                                } catch (NumberFormatException e) {
	                                    System.out.println("Entrada inválida para o salário bruto. Mantendo o salário atual: " + jogadorExistente.getSalarioBruto());
	                                    salarioBruto = jogadorExistente.getSalarioBruto();
	                                }
	                            }
	                            
	                         // Salário Recebido
	                            System.out.print("Digite o novo salário Recebido (ou pressione 'Enter' para manter o salário atual: " + jogadorExistente.getSalarioTotalRecebido() + "): ");
	                            String salarioTotalRecebidoInput = scanner.nextLine();
	                            if (salarioTotalRecebidoInput.isEmpty()) {
	                                salarioTotalRecebido = jogadorExistente.getSalarioBruto();
	                            } else {
	                                try {
	                                	salarioTotalRecebido = Double.parseDouble(salarioTotalRecebidoInput);
	                                } catch (NumberFormatException e) {
	                                    System.out.println("Entrada inválida para o salário recebido. Mantendo o salário atual: " + jogadorExistente.getSalarioTotalRecebido());
	                                    salarioTotalRecebido = jogadorExistente.getSalarioTotalRecebido();
	                                }
	                            }

	                            // Experiência
	                            System.out.print("Digite os novos anos de treino (ou pressione 'Enter' para manter o valor atual: " + jogadorExistente.getExperiencia() + "): ");
	                            String experienciaInput = scanner.nextLine();
	                            if (experienciaInput.isEmpty()) {
	                                experiencia = jogadorExistente.getExperiencia();
	                            } else {
	                                try {
	                                    experiencia = Integer.parseInt(experienciaInput);
	                                    if (experiencia < jogadorExistente.getExperiencia()) {
	                                        System.out.println("Anos de treino não podem ser diminuídos. Mantendo o valor atual: " + jogadorExistente.getExperiencia());
	                                        experiencia = jogadorExistente.getExperiencia();
	                                    }
	                                } catch (NumberFormatException e) {
	                                    System.out.println("Entrada inválida para os anos de treino. Mantendo o valor atual: " + jogadorExistente.getExperiencia());
	                                    experiencia = jogadorExistente.getExperiencia();
	                                }
	                            }

	                            // Categoria e equipe
	                            System.out.print("Digite a nova categoria (1 para solo e 2 para grupo, ou pressione Enter para manter a categoria atual: " + jogadorExistente.getCategoria() + "): ");
	                            String categoriaInput = scanner.nextLine();
	                            if (categoriaInput.isEmpty()) {
	                                categoriaId = jogadorExistente.getCategoria();
	                                equipeId = jogadorExistente.getEquipe();
	                            } else {
	                                try {
	                                    categoriaId = Integer.parseInt(categoriaInput);
	                                    if (categoriaId == 1) {
	                                        equipeId = 1;
	                                    } else if (categoriaId == 2) {
	                                        System.out.print("Digite o ID da equipe (diferente de 1, ou pressione Enter para manter a equipe atual: " + jogadorExistente.getEquipe() + "): ");
	                                        String equipeInput = scanner.nextLine();
	                                        if (equipeInput.isEmpty()) {
	                                            equipeId = jogadorExistente.getEquipe();
	                                        } else {
	                                            try {
	                                                equipeId = Integer.parseInt(equipeInput);
	                                                if (equipeId == 1) {
	                                                    System.out.println("Erro: Equipe ID 1 é reservado para categoria solo. Mantendo equipe atual: " + jogadorExistente.getEquipe());
	                                                    equipeId = jogadorExistente.getEquipe();
	                                                }
	                                            } catch (NumberFormatException e) {
	                                                System.out.println("Entrada inválida para o ID da equipe. Mantendo equipe atual: " + jogadorExistente.getEquipe());
	                                                equipeId = jogadorExistente.getEquipe();
	                                            }
	                                        }
	                                    } else {
	                                        System.out.println("Categoria inválida. Mantendo a categoria atual: " + jogadorExistente.getCategoria());
	                                        categoriaId = jogadorExistente.getCategoria();
	                                        equipeId = jogadorExistente.getEquipe();
	                                    }
	                                } catch (NumberFormatException e) {
	                                    System.out.println("Entrada inválida para a categoria. Mantendo a categoria atual: " + jogadorExistente.getCategoria());
	                                    categoriaId = jogadorExistente.getCategoria();
	                                    equipeId = jogadorExistente.getEquipe();
	                                }
	                            }

	                            Jogador jogadorAtualizado;
	                            if (experiencia >= 5) {
	                                jogadorAtualizado = new JogadorVeterano(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipeId, categoriaId);
	                            } else {
	                                jogadorAtualizado = new JogadorTrainee(id, nome, salarioBruto, salarioTotalRecebido, experiencia, equipeId, categoriaId);
	                            }
	                            jogadorFuncs.uptJogador(jogadorAtualizado);
	                            System.out.println("Jogador atualizado com sucesso.");
	                        } else {
	                            System.out.println("Jogador não encontrado.");
	                        }
	                    } catch (InputMismatchException e) {
	                        System.out.println("Erro de entrada. Certifique-se de digitar valores válidos.");
	                        scanner.nextLine(); // Limpa o buffer
	                    }
	                    break;
 
	                case 4:
	                    try {
	                        List<Jogador> jogadores = jogadorFuncs.listJogadores();
	                        if (jogadores.isEmpty()) {
	                            System.out.println("Nenhum jogador encontrado.");
	                            break;
	                        }

	                        for (Jogador j : jogadores) {
	                            System.out.println(j.getId() + " | Nome: " + j.getNome() + " | Salário bruto: " + j.getSalarioBruto() + " | Salario total recebido: " + j.getSalarioTotalRecebido() + " | Categoria: " + j.getCategoria() + " | Equipe: " + j.getEquipe());
	                        }

	                        System.out.print("Digite o id do jogador para deletar: ");
	                        id = scanner.nextInt();
	                        scanner.nextLine(); // Limpa o buffer

	                        Jogador jogadorParaDeletar = jogadorFuncs.searchJogador(id);
	                        if (jogadorParaDeletar != null) {
	                            jogadorFuncs.delJogador(id);
	                            System.out.println("Jogador deletado com sucesso.");
	                        } else {
	                            System.out.println("Erro: Jogador com o ID " + id + " não encontrado.");
	                        }
	                    } catch (InputMismatchException e) {
	                        System.out.println("Erro de entrada: Digite um número válido para o ID.");
	                        scanner.nextLine(); // Limpa o buffer em caso de erro de entrada
	                    } catch (Exception e) {
	                        System.out.println("Erro inesperado ao tentar deletar o jogador. Tente novamente.");
	                    }
	                    break;

	                case 5:
	                    try {
	                        List<Jogador> jogadores = jogadorFuncs.listJogadores();
	                        if (jogadores.isEmpty()) {
	                            System.out.println("Nenhum jogador encontrado.");
	                        } else {
	                            for (Jogador j : jogadores) {
	                                System.out.println(j.getId() + " | Nome: " + j.getNome() + " | Salário bruto: " + j.getSalarioBruto() + " | Salario total recebido:" + j.getSalarioTotalRecebido() + " | Categoria: " + j.getCategoria() + " | Equipe: " + j.getEquipe());
	                            }
	                        }
	                    } catch (Exception e) {
	                        System.out.println("Erro ao listar jogadores: " + e.getMessage());
	                        e.printStackTrace();
	                    }
	                    break;

	                case 6:
	                    try {
	                        List<Categoria> categorias = categoriaFuncs.listCategorias();
	                        if (categorias.isEmpty()) {
	                            System.out.println("Nenhuma categoria encontrada.");
	                        } else {
	                            for (Categoria c : categorias) {
	                                System.out.println(c.getNome() + " - Descrição: " + c.getDesc());
	                            }
	                        }
	                    } catch (Exception e) {
	                        System.out.println("Erro ao listar categorias: " + e.getMessage());
	                        e.printStackTrace();
	                    }
	                    break;

	                case 7:
	                    try {
	                        List<Equipe> equipes = equipeFuncs.listEquipes();
	                        if (equipes.isEmpty()) {
	                            System.out.println("Nenhuma equipe encontrada.");
	                        } else {
	                            for (Equipe eq : equipes) {
	                                System.out.println("ID " + eq.getId() + " | Nome: " + eq.getNome());
	                            }
	                        }
	                    } catch (Exception e) {
	                        System.out.println("Erro ao listar equipes: " + e.getMessage());
	                        e.printStackTrace();
	                    }
	                    break;
                        
	                case 8:
	                	List<Jogador> jogadores = jogadorFuncs.listJogadores();
                        if (jogadores.isEmpty()) {
                            System.out.println("Nenhum jogador encontrado.");
                        } else {
                            for (Jogador j : jogadores) {
                                System.out.println(j.getId() + " | Nome: " + j.getNome() + " | Salário bruto: " + j.getSalarioBruto() + " | Salario total recebido:" + j.getSalarioTotalRecebido() + " | Categoria: " + j.getCategoria() + " | Equipe: " + j.getEquipe());
                            }
                        }

                        System.out.print("Digite o ID do jogador para receber o salário: ");
                        id = scanner.nextInt();

                        boolean jogadorEncontrado = false;
                        for (Jogador j : jogadores) {
                            if (j.getId() == id) {
                                double salarioRecebido = j.getSalarioBruto();
                                jogadorEncontrado = true;
                                jogadorFuncs.receberSalario(id, salarioRecebido);
                                System.out.println("Salário recebido: " + salarioRecebido);
                                break;
                            }
                        }

                        if (!jogadorEncontrado) {
                            System.out.println("Jogador com ID " + id + " não encontrado.");
                        }
	                    break;
	                	
                    case 9:
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
