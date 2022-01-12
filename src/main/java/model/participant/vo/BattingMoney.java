package model.participant.vo;

import java.util.Objects;

public class BattingMoney {
    private final int value;

    public BattingMoney(final int value) {
        validateBattingMoney(value);
        this.value = value;
    }

    private void validateBattingMoney(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattingMoney that = (BattingMoney) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BattingMoney{" +
                "value=" + value +
                '}';
    }
}
