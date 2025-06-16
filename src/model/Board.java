package model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static model.GameStatusEnum.*;

public class Board {

    private final List<List<Space>> spaces;
    private final int size;

    public Board(final List<List<Space>> spaces) {
        if (spaces == null || spaces.isEmpty()) {
            throw new IllegalArgumentException("O tabuleiro não pode ser nulo ou vazio.");
        }
        this.spaces = spaces;
        this.size = spaces.size();
    }

    public List<List<Space>> getSpaces() {
        return spaces.stream()
                .map(Collections::unmodifiableList)
                .collect(Collectors.toUnmodifiableList());
    }

    public GameStatusEnum getStatus() {
        boolean hasEmptyCells = false;
        boolean userHasMadeMoves = false;

        for (List<Space> row : spaces) {
            for (Space space : row) {
                if (Objects.isNull(space.getActual())) {
                    hasEmptyCells = true;
                } else if (!space.isFixed()) {
                    userHasMadeMoves = true;
                }
            }
        }

        if (!userHasMadeMoves && !hasEmptyCells) {
            return COMPLETE;
        }

        if (!userHasMadeMoves) {
            return NON_STARTED;
        }

        return hasEmptyCells ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors() {
        return spaces.stream()
                .flatMap(List::stream)
                .anyMatch(Space::hasError);
    }

    public boolean changeValue(final int row, final int col, final int value) {
        return getSpaceAt(row, col).map(space -> {
            if (space.isFixed()) {
                return false;
            }
            space.setActual(value);
            return true;
        }).orElse(false);
    }

    public boolean clearValue(final int row, final int col) {
        return getSpaceAt(row, col).map(space -> {
            if (space.isFixed()) {
                return false;
            }
            space.clearSpace();
            return true;
        }).orElse(false);
    }

    public void reset() {
        spaces.stream()
                .flatMap(List::stream)
                .filter(space -> !space.isFixed())
                .forEach(Space::clearSpace);
    }

    public boolean isGameFinished() {
        return !hasErrors() && getStatus() == COMPLETE;
    }

    private Optional<Space> getSpaceAt(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < spaces.get(row).size()) {
            return Optional.of(spaces.get(row).get(col));
        }
        return Optional.empty();
    }
}