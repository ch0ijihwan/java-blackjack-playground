package model.participant;

import model.card.Cards;
import model.participant.state.running.Hit;

public class Dealer extends Participant {
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer(final Cards initCards) {
        super("딜러");
        state = new Hit(initCards);
    }

    public String getOneCardStatus() {
        return state.getCards()
                .getCards()
                .get(FIRST_CARD_INDEX).toString();
    }

    public int getScore() {
        return state.getCards().getScore();
    }

    @Override
    public boolean canDrawCard() {
        return state.getCards().getScore() <= 16;
    }

    @Override
    public Cards getCards() {
        return state.getCards();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
