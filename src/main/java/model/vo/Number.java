package model.vo;

public enum Number {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10),
    QUEEN(11),
    KING(12);

    private final int value;

    Number(int number) {
        this.value = number;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public boolean isJQK() {
        return this == JACK || this == QUEEN || this == KING;
    }

    public int getValue() {
        return value;
    }
}
