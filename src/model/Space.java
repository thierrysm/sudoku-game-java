package model;

import java.util.Objects;

public class Space {

    private Integer actual;
    private final int expected;
    private final boolean fixed;

    public Space(final int expected, boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if (fixed) {
            this.actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        if (fixed) {
            return;
        }
        this.actual = actual;
    }

    public void clearSpace() {
        setActual(null);
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean hasError() {
        return Objects.nonNull(actual) && !actual.equals(expected);
    }

    @Override
    public String toString() {
        return String.format("Space[Actual: %d, Expected: %d, Fixed: %b]", actual, expected, fixed);
    }
}