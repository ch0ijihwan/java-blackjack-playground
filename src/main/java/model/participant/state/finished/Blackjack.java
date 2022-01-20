package model.participant.state.finished;

import model.card.Cards;
import model.participant.vo.BattingMoney;

public class Blackjack extends Finished {
    public Blackjack(Cards initCards) {
        super(initCards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getProfit(BattingMoney battingMoney, Cards dealerCards) {
        if (dealerCards.isBlackJack()) {
            return (int) (battingMoney.getValue() * 1.5);
        }
        return 0;
    }
}
