package model.card;

import model.card.vo.PlayingCard;
import model.card.vo.Score;

import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<PlayingCard> playingCards;
    private Score score;

    public Cards(final List<PlayingCard> initPlayingCards) {
        playingCards = initPlayingCards;
        score = Score.of(initPlayingCards);
    }

    public void add(final PlayingCard inputtedCard) {
        playingCards.add(inputtedCard);
        this.score = Score.of(getCards());
    }

    public int getScore() {
        return score.getValue();
    }

    public boolean canDrawCard() {
        return score.isLessThen21();
    }

    public List<PlayingCard> getCards() {
        return playingCards;
    }

    public boolean isBurst() {
        return score.isBiggerThen21();
    }

    public boolean isBlackJack() {
        return playingCards.size() == 2 && score.is21();
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
        return String.join(",", playingCards.toString());
    }
}
