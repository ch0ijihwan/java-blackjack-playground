package model.card;

import model.card.vo.Card;
import model.card.vo.Score;

import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> playingCards;
    private Score score;

    public Cards(final List<Card> initCards) {
        playingCards = initCards;
        score = Score.of(initCards);
    }

    public void add(final Card inputtedCard) {
        playingCards.add(inputtedCard);
        this.score = Score.of(getPlayingCards());
    }

    public int getScore() {
        return score.getValue();
    }

    public boolean canDrawCard() {
        return score.isLessThen21();
    }

    public List<Card> getPlayingCards() {
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
        return Objects.equals(playingCards, cards.playingCards) && Objects.equals(score, cards.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCards, score);
    }

    @Override
    public String toString() {
        return String.join(",", playingCards.toString());
    }
}
