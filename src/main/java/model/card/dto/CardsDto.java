package model.card.dto;

import model.card.Cards;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CardsDto {
    private final List<CardDto> cardDtoList;
    private final int score;


    public static CardsDto from(Cards cards) {
        return new CardsDto(cards);
    }

    private CardsDto(Cards cards) {
        cardDtoList = cards.getPlayingCards()
                .stream()
                .map(CardDto::from)
                .collect(Collectors.toUnmodifiableList());
        score = cards.getScore();
    }

    public List<CardDto> getCardDtoList() {
        return cardDtoList;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardsDto cardsDto = (CardsDto) o;
        return score == cardsDto.score && Objects.equals(cardDtoList, cardsDto.cardDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardDtoList, score);
    }

    @Override
    public String toString() {
        return "CardsDto{" +
                "cardDtoList=" + cardDtoList +
                ", score=" + score +
                '}';
    }
}
