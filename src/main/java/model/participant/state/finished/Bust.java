package model.participant.state.finished;

import model.card.Cards;
import model.participant.vo.BattingMoney;

public class Bust extends Finished {
    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getProfit(final BattingMoney battingMoney, final Cards dealerCards) {
        if (dealerCards.isBurst()) {
            return 0;
        }
        return battingMoney.getValue() * -1;
    }
}
