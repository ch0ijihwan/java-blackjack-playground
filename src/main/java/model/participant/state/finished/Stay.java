package model.participant.state.finished;

import model.card.Cards;
import model.participant.vo.BattingMoney;

public class Stay extends Finished {
    public Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getProfit(final BattingMoney battingMoney, final Cards dealerCards) {
        if (dealerCards.getScore() == cards.getScore()) {
            return 0;
        } else if (dealerCards.isBurst()) {
            return battingMoney.getValue();
        } else if (dealerCards.isBlackJack() || cards.getScore() < dealerCards.getScore()) {
            return battingMoney.getValue() * -1;
        }
        return battingMoney.getValue();
    }
}
