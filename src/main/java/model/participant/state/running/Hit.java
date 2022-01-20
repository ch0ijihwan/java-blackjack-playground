package model.participant.state.running;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.state.State;
import model.participant.state.finished.Bust;
import model.participant.state.finished.Stay;

public class Hit extends Running{
    protected Hit(Cards initCards) {
        super(initCards);
    }

    @Override
    public State draw(PlayingCard card) {
        cards.add(card);
        if(cards.isBurst()){
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
