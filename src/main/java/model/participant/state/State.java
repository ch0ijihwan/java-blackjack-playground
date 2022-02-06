package model.participant.state;

import model.card.Cards;
import model.card.vo.Card;
import model.participant.vo.BattingMoney;

public interface State {
    State draw(final Card card);

    State stay();

    boolean isFinished();

    Cards getCards();

    int getProfit(final BattingMoney battingMoney, final Cards dealerCards);
}
