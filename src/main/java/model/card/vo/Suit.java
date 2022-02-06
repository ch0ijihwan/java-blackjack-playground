package model.card.vo;

public enum Suit {
    SPADE("Spade"),
    HEART("Heart"),
    DIAMOND("Diamond"),
    CLUB("Club");

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
