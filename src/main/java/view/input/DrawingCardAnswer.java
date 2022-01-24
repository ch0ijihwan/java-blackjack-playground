package view.input;

import java.util.Arrays;
import java.util.Objects;

public enum DrawingCardAnswer {
    YES("y"),
    NO("n");
    private final String value;

    DrawingCardAnswer(final String value) {
        this.value = value;
    }

    public static DrawingCardAnswer of(final String value) {
        return Arrays.stream(values())
                .filter(drawingCardAnswer -> Objects.equals(drawingCardAnswer.value, value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력은 y 혹은 n 이어야만 합니다."));
    }
}
