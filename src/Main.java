import model.Board;
import model.Space;

import java.util.*;

import static java.util.Objects.isNull;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Board board;
    private static final int BOARD_SIZE = 9;

    public static void main(String[] args) {
        loadGame(args);

        while (true) {
            printMenu();
            String optionStr = scanner.next();
            scanner.nextLine();

            int option;
            try {
                option = Integer.parseInt(optionStr);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, por favor, digite um número.");
                continue;
            }

            switch (option) {
                case 1 -> startGame();
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> {
                    System.out.println("Obrigado por jogar. Até mais!");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, selecione uma das opções do menu.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Menu Sudoku ---");
        System.out.println("1 - Iniciar um novo Jogo (ou reiniciar o atual)");
        System.out.println("2 - Colocar um número");
        System.out.println("3 - Remover um número");
        System.out.println("4 - Visualizar jogo atual");
        System.out.println("5 - Verificar status do jogo");
        System.out.println("6 - Limpar preenchimento do jogo");
        System.out.println("7 - Finalizar e verificar o jogo");
        System.out.println("8 - Sair");
        System.out.print("Selecione uma opção: ");
    }

    private static void loadGame(String[] args) {
        System.out.println("Carregando tabuleiro...");
        List<List<Space>> spaces = new ArrayList<>();
        Map<String, String> positions;

        if (args.length > 0) {
            System.out.println("Usando configuração via argumentos.");
            positions = new HashMap<>();
            for (String arg : args) {
                String[] parts = arg.split(";");
                if (parts.length == 2) {
                    positions.put(parts[0], parts[1]);
                }
            }
        } else {
            System.out.println("Nenhum argumento fornecido. Usando tabuleiro padrão.");
            positions = getDefaultBoardConfig();
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                String key = String.format("%d,%d", i, j);
                String positionConfig = positions.get(key);

                Space currentSpace;
                if (positionConfig != null && !positionConfig.isEmpty()) {
                    String[] configParts = positionConfig.split(",");
                    int expected = Integer.parseInt(configParts[0]);
                    boolean isFixed = Boolean.parseBoolean(configParts[1]);
                    currentSpace = new Space(expected, isFixed);
                } else {
                    currentSpace = new Space(0, false);
                }
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
    }

    private static Map<String, String> getDefaultBoardConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("0,0", "5,true");   config.put("0,1", "3,true");   /* ... */ config.put("0,4", "7,true");
        config.put("1,0", "6,true");   /* ... */ config.put("1,3", "1,true");   config.put("1,4", "9,true");   config.put("1,5", "5,true");
        return config;
    }

    private static void startGame() {
        if (isNull(board)) {
            System.out.println("Erro: o tabuleiro não foi carregado. Tente reiniciar o programa.");
            return;
        }
        board.reset();
        System.out.println("O jogo foi iniciado/reiniciado! Boa sorte!");
        showCurrentGame();
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Informe a LINHA em que o número será inserido (0-8):");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a COLUNA em que o número será inserido (0-8):");
        var col = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s,%s]:\n", row, col);
        var value = runUntilGetValidNumber(1, 9);

        if (!board.changeValue(row, col, value)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo e não pode ser alterada.\n", row, col);
        } else {
            System.out.println("Valor inserido com sucesso!");
            showCurrentGame();
        }
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Informe a LINHA da qual o número será removido (0-8):");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a COLUNA da qual o número será removido (0-8):");
        var col = runUntilGetValidNumber(0, 8);

        if (!board.clearValue(row, col)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo e não pode ser alterada.\n", row, col);
        } else {
            System.out.println("Valor removido com sucesso!");
            showCurrentGame();
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Seu jogo se encontra da seguinte forma:");
        List<List<Space>> spaces = board.getSpaces(); // Pega a cópia imutável

        System.out.println("    0 1 2   3 4 5   6 7 8");
        System.out.println("  +-------+-------+-------+");
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i > 0 && i % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
            System.out.print(i + " | ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                Integer actualValue = spaces.get(i).get(j).getActual();
                System.out.print((isNull(actualValue) ? " " : actualValue) + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        System.out.println("  +-------+-------+-------+");
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status: %s\n", board.getStatus());
        if (board.hasErrors()) {
            System.out.println("Atenção: O jogo contém erros!");
        } else {
            System.out.println("O jogo não contém erros.");
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)");
        String confirm = scanner.next();
        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("Seu progresso foi apagado. O tabuleiro voltou ao estado inicial.");
            showCurrentGame();
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        if (board.isGameFinished()) {
            System.out.println("PARABÉNS! Você concluiu o jogo com sucesso!");
            showCurrentGame();
            System.out.println("Deseja iniciar um novo jogo? (sim/não)");
            if(scanner.next().equalsIgnoreCase("sim")) {
                loadGame(new String[0]);
                startGame();
            } else {
                System.exit(0);
            }
        } else if (board.hasErrors()) {
            System.out.println("Não foi possível finalizar. Seu jogo contém erros, verifique o tabuleiro e ajuste-o.");
        } else {
            System.out.println("Ainda não! Você precisa preencher todos os espaços em branco para finalizar.");
        }
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        int current = -1;
        while (true) {
            try {
                current = scanner.nextInt();
                if (current >= min && current <= max) {
                    break;
                } else {
                    System.out.printf("Número fora do intervalo. Informe um número entre %s e %s:\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.printf("Entrada inválida. Informe um número inteiro entre %s e %s:\n", min, max);
                scanner.next();
            }
        }
        return current;
    }
}