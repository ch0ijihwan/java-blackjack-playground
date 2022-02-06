package model.participant.state.finished;

import model.card.Cards;
import model.participant.vo.BattingMoney;

public class Blackjack extends Finished {
    public Blackjack(final Cards initCards) {
        super(initCards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getProfit(final BattingMoney battingMoney, final Cards dealerCards) {
        if (dealerCards.isBlackJack()) {
            return 0;
        }
        return (int) (battingMoney.getValue() * 1.5);
    }
}
