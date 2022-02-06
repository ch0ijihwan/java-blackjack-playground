package model.participant;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.state.State;
import model.participant.vo.Name;

public abstract class Participant {
    State state;
    Name name;

    Participant(final String name) {
        this.name = new Name(name);
    }

    public abstract boolean canDrawCard();

    public Cards getCards() {
        return state.getCards();
    }

    public String getNameValue() {
        return name.getValue();
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }
}
