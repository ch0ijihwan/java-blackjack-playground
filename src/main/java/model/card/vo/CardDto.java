package model.card.vo;

public class CardDto {
    private final int number;
    private final String suit;

    public static CardDto from(Card card){
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
}
