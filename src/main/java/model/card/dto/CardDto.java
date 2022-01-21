package model.card.dto;

import model.card.vo.Card;

import java.util.Objects;

public class CardDto {
    private final int number;
    private final String suit;

    public static CardDto from(Card card) {
        return new CardDto(card);
    }

    private CardDto(Card card) {
        this.number = card.getNumberValue();
        this.suit = card.getSuitValue();
    }

    public int getCardNumber() {
        return number;
    }

    public String getCardSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return number == cardDto.number && Objects.equals(suit, cardDto.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit);
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "number=" + number +
                ", suit='" + suit + '\'' +
                '}';
    }
}
