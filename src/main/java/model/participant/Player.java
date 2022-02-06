package model.participant;

import model.card.Cards;
import model.participant.state.finished.Blackjack;
import model.participant.state.running.Hit;
import model.participant.vo.BattingMoney;

import java.util.Objects;

public class Player extends Participant {
    private final BattingMoney battingMoney;

    public Player(final String inputtedName, final int inputtedBattingMoney, final Cards initCards) {
        super(inputtedName);
        if (initCards.isBlackJack()) {
            state = new Blackjack(initCards);
        } else if (initCards.canDrawCard()) {
            state = new Hit(initCards);
        }
        battingMoney = new BattingMoney(inputtedBattingMoney);
    }

    public void stay() {
        state = state.stay();
    }

    @Override
    public boolean canDrawCard() {
        return state.getCards().canDrawCard();
    }

    public int getDividends(final Cards dealerCards) {
        return state.getProfit(this.battingMoney, dealerCards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(battingMoney, player.battingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(battingMoney);
    }

    @Override
    public String toString() {
        return "Player{" +
                "battingMoney=" + battingMoney +
                '}';
    }
}
