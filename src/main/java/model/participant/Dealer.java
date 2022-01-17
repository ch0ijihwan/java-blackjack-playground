package model.participant;

import model.card.Cards;
import model.card.vo.PlayingCard;

import java.util.List;
import java.util.Objects;

public class Dealer extends Participant {
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer(final List<PlayingCard> cards) {
        super("딜러", cards);
    }

    public String getOneCardStatus() {
        return cards.getCards().get(FIRST_CARD_INDEX).toString();
    }

    public int getScore() {
        return cards.getScore();
    }

    @Override
    public boolean canDrawCard() {
        return cards.getScore() <= 16;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String
    toString() {
        return "Dealer{" +
                "cards=" + cards +
                '}';
    }
}
