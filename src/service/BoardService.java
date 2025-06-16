package service;

import model.Board;
import model.GameStatusEnum;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BoardService {

    private static final int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        if (gameConfig == null || gameConfig.isEmpty()) {
            throw new IllegalArgumentException("A configuração do jogo não pode ser nula ou vazia.");
        }
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() {
        return board.getSpaces();
    }

    public boolean hasErrors() {
        return board.hasErrors();
    }

    public GameStatusEnum getStatus() {
        return board.getStatus();
    }

    public boolean isGameFinished() {
        return board.isGameFinished();
    }

    public boolean changeValue(int row, int col, int value) {
        return board.changeValue(row, col, value);
    }

    public boolean clearValue(int row, int col) {
        return board.clearValue(row, col);
    }

    public void reset() {
        board.reset();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String key = String.format("%d,%d", i, j);
                String positionConfig = gameConfig.get(key);
                if (Objects.isNull(positionConfig)) {
                    throw new IllegalArgumentException(
                            "Configuração inválida: falta a definição para a célula na posição [" + i + "," + j + "]"
                    );
                }

                try {
                    String[] parts = positionConfig.split(",");
                    int expected = Integer.parseInt(parts[0]);
                    boolean fixed = Boolean.parseBoolean(parts[1]);
                    spaces.get(i).add(new Space(expected, fixed));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException(
                            "Formato de configuração inválido para a chave '" + key + "'. Esperado: 'valor,booleano'", e
                    );
                }
            }
        }
        return spaces;
    }
}