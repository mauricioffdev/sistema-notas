package com.sistemanotas;
import java.util.ArrayList;
import java.util.Scanner;

public class Boletim {
    private ArrayList<Aluno> alunos;
    private Scanner scanner;

    public Boletim() {
        alunos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void executar() {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Adicionar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Verificar notas e médias de um aluno");
            System.out.println("4 - Editar aluno");
            System.out.println("5 - Excluir aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    adicionarAluno();
                    break;
                case "2":
                    listarAlunos();
                    break;
                case "3":
                    verificarAluno();
                    break;
                case "4":
                    editarAluno();
                    break;
                case "5":
                    excluirAluno();
                    break;

                case "0":
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarAluno() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.println("Digite as notas:");
        double nota1 = lerNota("Nota 1: ");
        double nota2 = lerNota("Nota 2: ");
        double nota3 = lerNota("Nota 3: ");

        Aluno aluno = new Aluno(nome, nota1, nota2, nota3);
        alunos.add(aluno);

        // 💾 Salvar no banco de dados
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.salvar(aluno);

        System.out.println("Aluno adicionado com sucesso.");
    }


    private void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("Lista de alunos:");
            for (int i = 0; i < alunos.size(); i++) {
                Aluno a = alunos.get(i);
                System.out.printf("%d - %s\n", i, a.getNome());
            }
        }
    }

    private void verificarAluno() {
        listarAlunos();
        if (alunos.isEmpty()) return;

        System.out.print("Digite o número do aluno que deseja verificar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine());
            Aluno aluno = alunos.get(indice);
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Notas: " + aluno.getNota1() + ", " + aluno.getNota2() + ", " + aluno.getNota3());
            System.out.printf("Média: %.2f - Situação: %s\n", aluno.calcularMedia(), aluno.situacao());
        } catch (Exception e) {
            System.out.println("Número inválido.");
        }
    }

    private void editarAluno() {
        listarAlunos();
        if (alunos.isEmpty()) return;

        System.out.print("Digite o número do aluno que deseja editar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine());
            Aluno aluno = alunos.get(indice);

            System.out.print("Novo nome (pressione Enter para manter: " + aluno.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                aluno.setNome(novoNome);
            }

            System.out.print("Nova nota 1 (pressione Enter para manter: " + aluno.getNota1() + "): ");
            String inputNota1 = scanner.nextLine();
            if (!inputNota1.isEmpty()) {
                aluno.setNota1(parseNota(inputNota1));
            }

            System.out.print("Nova nota 2 (pressione Enter para manter: " + aluno.getNota2() + "): ");
            String inputNota2 = scanner.nextLine();
            if (!inputNota2.isEmpty()) {
                aluno.setNota2(parseNota(inputNota2));
            }

            System.out.print("Nova nota 3 (pressione Enter para manter: " + aluno.getNota3() + "): ");
            String inputNota3 = scanner.nextLine();
            if (!inputNota3.isEmpty()) {
                aluno.setNota3(parseNota(inputNota3));
            }

            System.out.println("Aluno editado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao editar aluno. Verifique o número digitado.");
        }
    }

    private void excluirAluno() {
    if (alunos.isEmpty()) {
        System.out.println("Nenhum aluno cadastrado para excluir.\n");
        return;
    }

    listarAlunos();
    System.out.print("Digite o número do aluno que deseja excluir: ");
    String entrada = scanner.nextLine();
    int index;

    try {
        index = Integer.parseInt(entrada);
        if (index >= 0 && index < alunos.size()) {
            Aluno removido = alunos.remove(index);
            System.out.println("Aluno '" + removido.getNome() + "' excluído com sucesso.\n");
        } else {
            System.out.println("Índice inválido. Nenhum aluno foi excluído.\n");
        }
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, digite um número.\n");
    }
}


    private double lerNota(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine().replace(",", ".");
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Nota inválida. Digite um número válido.");
            }
        }
    }

    private double parseNota(String input) {
        try {
            return Double.parseDouble(input.replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Nota mantida.");
            return 0;
        }
    }
}
