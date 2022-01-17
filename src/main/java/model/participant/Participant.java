package model.participant;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.vo.Name;

import java.util.List;

public abstract class Participant {
    final Name name;
    final Cards cards;

    Participant(final String name, final List<PlayingCard> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public abstract boolean canDrawCard();

    public Cards getCards() {
        return this.cards;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public void draw(final PlayingCard card) {
        cards.add(card);
    }
}
