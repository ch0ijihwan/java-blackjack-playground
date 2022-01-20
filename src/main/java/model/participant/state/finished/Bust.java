package model.participant.state.finished;

import model.card.Cards;
import model.participant.state.State;
import model.participant.vo.BattingMoney;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getProfit(BattingMoney battingMoney, Cards dealerCards) {
        if(dealerCards.isBurst()){
             return 0;
        }
        return battingMoney.getValue() * -1;
    }
}
