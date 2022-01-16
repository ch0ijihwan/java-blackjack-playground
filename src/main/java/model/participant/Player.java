package model.participant;

import model.card.Cards;
import model.card.vo.PlayingCard;
import model.participant.vo.BattingMoney;
import model.participant.vo.Name;

import java.util.List;
import java.util.Objects;

public class Player {
    private final Name name;
    private final Cards cards;
    private final BattingMoney battingMoney;
    private boolean isStay = false;

    public Player(final String name, final int battingMoney, final List<PlayingCard> cards) {
        this.name = new Name(name);
        this.battingMoney = new BattingMoney(battingMoney);
        this.cards = new Cards(cards);
    }

    public Cards getCards() {
        return this.cards;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public void draw(final PlayingCard card) {
        cards.add(card);
        if (!cards.canDrawCard()) {
            stay();
        }
    }

    public void stay() {
        isStay = true;
    }

    public boolean isStay() {
        return isStay;
    }

    public int battle(final Cards dealerCards) {
        if (this.cards.isBurst() || dealerCards.getScore() > cards.getScore()) {
            return battingMoney.getValue() * -1;
        } else if (this.cards.isBlackJack()) {
            return (int) (battingMoney.getValue() * 1.5);
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
