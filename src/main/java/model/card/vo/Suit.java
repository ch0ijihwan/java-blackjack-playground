package model.card.vo;

public enum Suit {
    SPADE("spade"),
    HEART("heart"),
    DIAMOND("diamond"),
    CLUB("club");

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
