package model.participant.state.running;

import model.card.Cards;
import model.participant.state.Started;
import model.participant.vo.BattingMoney;

public abstract class Running extends Started {
    protected Running(Cards initCards) {
        super(initCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int getProfit(BattingMoney battingMoney, Cards dealerCards) {
        throw new IllegalStateException("Running(카드를 뽑는 중) 상태이기 때문에, 아직 이익금을 알 수 없습니다.");
    }
}
