package model.card.vo;

import java.util.Objects;

public class PlayingCard {
    private final Suit suit;
    private final Number number;

    private PlayingCard(final Suit suit, final Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static PlayingCard makeCard(final Suit suit, final Number number) {
        return new PlayingCard(suit, number);
    }

    public boolean isAce() {
        return this.number.isAce();
    }

    public Number getNumber() {
        return this.number;
    }

    public int getNumberValue() {
        return this.number.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard card = (PlayingCard) o;
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }

    @Override
    public String toString() {
        return suit.toString() + number.getValue();
    }
}
