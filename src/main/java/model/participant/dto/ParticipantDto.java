package model.participant.dto;

import model.card.Cards;
import model.card.dto.CardDto;
import model.card.dto.CardsDto;
import model.participant.Participant;

import java.util.List;
import java.util.Objects;

public class ParticipantDto {
    private final int score;
    private final String name;
    private final CardsDto cardsDto;

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getNameValue(), participant.getCards());
    }

    private ParticipantDto(String name, Cards cards) {
        this.score = cards.getScore();
        this.name = name;
        this.cardsDto = CardsDto.from(cards);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtoList() {
        return cardsDto.getCardDtoList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantDto that = (ParticipantDto) o;
        return score == that.score && Objects.equals(name, that.name) && Objects.equals(cardsDto, that.cardsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, name, cardsDto);
    }

    @Override
    public String toString() {
        return "ParticipantDto{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", cardsDto=" + cardsDto +
                '}';
    }
}
