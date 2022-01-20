package model.participant.state.finished;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.state.Started;
import model.participant.state.State;

public abstract class Finished extends Started {
    protected Finished(Cards initCards) {
        super(initCards);
    }

    public State draw(PlayingCard card) {
        throw new IllegalStateException("게임이 끝난 상태입니다.");
    }

    public State stay() {
        throw new IllegalStateException("게임이 끝난 상태입니다.");
    }
}
