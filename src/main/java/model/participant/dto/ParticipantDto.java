package model.participant.dto;

import model.participant.Dealer;
import model.participant.Player;

public class ParticipantDto {
    private final int score;
    private final String name;
    private final String cardsStatus;

    public static ParticipantDto from(Player player) {
        return new ParticipantDto(player.getNameValue(), player.getCards().toString(), player.getCards().getScore());
    }

    public static ParticipantDto from(Dealer dealer) {
        return new ParticipantDto(dealer.getNameValue(), dealer.getCards().toString(), dealer.getCards().getScore());
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
