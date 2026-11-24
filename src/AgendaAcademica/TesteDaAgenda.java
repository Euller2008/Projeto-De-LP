package AgendaAcademica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TesteDaAgenda {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        AtividadeService service = new AtividadeServiceImpl();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== AGENDA ACADÊMICA ===");
            System.out.println("1 - Cadastrar atividade");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Listar pendentes");
            System.out.println("4 - Buscar por título");
            System.out.println("5 - Marcar como concluída");
            System.out.println("6 - Remover atividade");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrar(service, scanner);
                case 2 -> exibir(service.listarTodas());
                case 3 -> exibir(service.listarPendentes());
                case 4 -> buscar(service, scanner);
                case 5 -> concluir(service, scanner);
                case 6 -> remover(service, scanner);
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrar(AtividadeService service, Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Matéria: ");
        String materia = scanner.nextLine();
        System.out.print("Data de entrega (dd/MM/yyyy): ");

        try {
            LocalDate data = LocalDate.parse(scanner.nextLine(), FORMATO);
            service.cadastrar(new Atividades(titulo, materia, data));
            System.out.println("Atividade cadastrada!");
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    private static void exibir(List<Atividades> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma atividade encontrada.");
            return;
        }
        for (Atividades a : lista) {
            imprimir(a);
        }
    }

    private static void buscar(AtividadeService service, Scanner scanner) {
        System.out.print("Título: ");
        try {
            imprimir(service.buscarPorTitulo(scanner.nextLine()));
        } catch (AtividadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void concluir(AtividadeService service, Scanner scanner) {
        System.out.print("Título: ");
        try {
            service.marcarComoConcluida(scanner.nextLine());
            System.out.println("Atividade marcada como concluída!");
        } catch (AtividadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void remover(AtividadeService service, Scanner scanner) {
        System.out.print("Título: ");
        try {
            service.remover(scanner.nextLine());
            System.out.println("Atividade removida!");
        } catch (AtividadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void imprimir(Atividades a) {
        String situacao = a.isConcluida() ? "Concluída" : "Pendente";
        long dias = a.getDiasRestantes();
        String prazo = dias >= 0 ? "faltam " + dias + " dia(s)" : "atrasada há " + Math.abs(dias) + " dia(s)";
        System.out.println("- " + a.getTitulo() + " | " + a.getMateria()
                + " | entrega: " + a.getDataEntrega().format(FORMATO)
                + " | " + situacao + " | " + prazo);
    }
}