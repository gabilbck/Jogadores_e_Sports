package dominio_laiz_e_gabrieli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogador", "root", "password");
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
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o código: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();  // Limpa o buffer
                        System.out.print("Digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o salário: ");
                        double salario = scanner.nextDouble();
                        System.out.print("Digite os anos de treino: ");
                        int anosTreino = scanner.nextInt();
                        System.out.print("Digite a categoria (1 para solo e 2 para grupo): ");
                        int categoria = scanner.nextInt();

                        Jogador jogador;
                        if (anosTreino >= 5) {
                            jogador = new JogadorVeterano(codigo, nome, salario, anosTreino, categoria);
                        } else {
                            jogador = new JogadorTrainee(codigo, nome, salario, anosTreino, categoria);
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
                        // Similar à adição, mas com atualização
                        // fazer depois
                        break;

                    case 4:
                        System.out.print("Digite o código do jogador para deletar: ");
                        int codigoDeletar = scanner.nextInt();
                        jogadorFuncs.delJogador(codigoDeletar);
                        System.out.println("Jogador deletado com sucesso.");
                        break;

                    case 5:
                        List<Jogador> jogador = jogadorFuncs.listJogadores();
                        for (Jogador j : jogadores) {
                            System.out.println(j.getNome() + " - Salário: " + j.calcularSalario() + " - Categoria Jogada: " + j.getCategoria());
                        }
                        break;

                    case 6:
                        List<Categoria> categoria = categoriaFuncs.listCategorias();
                        for (Categoria j : categoria) {
                            System.out.println(j.getCategoria() + " - Descrição: " + j.getDescricao());
                        }
                        break;
                        
                    case 7:
                        running = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
