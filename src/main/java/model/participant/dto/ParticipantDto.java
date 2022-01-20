package model.participant.dto;

import model.participant.Participant;

public class ParticipantDto {
    private final int score;
    private final String name;
    private final String cardsStatus;

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getNameValue(), participant.getCards().toString(), participant.getCards().getScore());
    }

    private ParticipantDto(String name, String cardsStatus, int score) {
        this.score = score;
        this.name = name;
        this.cardsStatus = cardsStatus;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getCardsStatus() {
        return cardsStatus;
    }
}
