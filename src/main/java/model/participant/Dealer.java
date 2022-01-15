package model.participant;

import model.card.Cards;
import model.card.vo.PlayingCard;

import java.util.List;
import java.util.Objects;

public class Dealer {
    private final Cards cards;

    public Dealer(final List<PlayingCard> cards) {
        this.cards = new Cards(cards);
    }

    public boolean  isScoreLessThan16() {
        return cards.getScore() <= 16;
    }

    public void draw(final PlayingCard card) {
        cards.add(card);
    }
    public String getOneCardStatus(){
        return cards.getCards().get(0).toString();
    }
    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.getScore();
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
