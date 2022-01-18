package model.participant;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.vo.BattingMoney;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {
    private final BattingMoney battingMoney;

    public Player(final String inputtedName, final int inputtedBattingMoney, final List<PlayingCard> initCards) {
        super(inputtedName, initCards);
        battingMoney = new BattingMoney(inputtedBattingMoney);
    }

    @Override
    public boolean canDrawCard() {
        return cards.canDrawCard();
    }

    public int getDividends(final Cards dealerCards) {
        if (this.cards.isBurst() || dealerCards.getScore() > cards.getScore()) {
            return battingMoney.getValue() * -1;
        } else if (this.cards.isBlackJack()) {
            return (int) (battingMoney.getValue() * 1.5);
        } else if (dealerCards.isBurst()) {
            return battingMoney.getValue();
        }
        return battingMoney.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards) && Objects.equals(battingMoney, player.battingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards, battingMoney);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                ", battingMoney=" + battingMoney +
                '}';
    }
}
