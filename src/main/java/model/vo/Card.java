package model.vo;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Number number;

    private Card(final Suit suit, final Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static Card makeCard(final Suit suit, final Number number) {
        return new Card(suit, number);
    }

    public boolean isAce() {
        return this.number.isAce();
    }

    public Number getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", number=" + number +
                '}';
    }
}
