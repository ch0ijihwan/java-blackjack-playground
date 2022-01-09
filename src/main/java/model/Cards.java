package model;

import model.vo.PlayingCard;

import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<PlayingCard> playingCards;
    private int score = 0;
    private int notChangedAceCount = 0;
    private static final int DIFFERENCE_IN_CHANGE_OF_ACE = 10;

    public Cards(List<PlayingCard> inputtedPlayingCards) {
        playingCards = inputtedPlayingCards;
        calculateInitAceCount(inputtedPlayingCards);
        calculateTotalScore();
    }

    private void calculateInitAceCount(final List<PlayingCard> playingCards) {
        notChangedAceCount = (int) playingCards.stream()
                .filter(PlayingCard::isAce)
                .count();
    }

    private void calculateTotalScore() {
        score = playingCards.stream()
                .mapToInt(PlayingCard::getNumberValue)
                .sum();
    }

    public void add(final PlayingCard inputtedCard) {
        if (inputtedCard.isAce()) {
            notChangedAceCount++;
        }
        playingCards.add(inputtedCard);
        calculateTotalScore();
    }


    public int getScore() {
        return score;
    }

    public void changeAceScore() {
        if (notChangedAceCount > 0) {
            score = score - DIFFERENCE_IN_CHANGE_OF_ACE;
            notChangedAceCount--;
        }
    }

    public boolean hasAce() {
        return notChangedAceCount > 0;
    }

    public boolean isOver21() {
        return score > 21;
    }

    public boolean isBlackJack() {
        return playingCards.size() == 2 && score == 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return Objects.equals(playingCards, cards.playingCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCards);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "playingCards=" + playingCards +
                '}';
    }
}
