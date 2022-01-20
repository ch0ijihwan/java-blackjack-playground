package model.participant.state;

import model.card.Cards;

import java.util.Objects;

public abstract class Started implements State {
    protected final Cards cards;

    protected Started(Cards initCards) {
        this.cards = initCards;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Started started = (Started) o;
        return Objects.equals(cards, started.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Started{" +
                "cards=" + cards +
                '}';
    }
}
