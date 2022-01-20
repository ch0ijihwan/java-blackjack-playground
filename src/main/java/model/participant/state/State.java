package model.participant.state;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.vo.BattingMoney;

public interface State {
    State draw(PlayingCard card);

    State stay();

    boolean isFinished();

    Cards getCards();

    int getProfit(final BattingMoney battingMoney, final Cards dealerCards);
}
