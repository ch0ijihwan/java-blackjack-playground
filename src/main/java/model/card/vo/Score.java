package model.card.vo;

import java.util.List;
import java.util.Objects;

public class Score {
    private static final int DIFFERENCE_IN_CHANGE_OF_ACE = 10;
    private final int value;

    public static Score of(final List<Card> cards) {
        return new Score(cards);
    }

    private Score(final List<Card> cards) {
        value = calculateTotalScore(cards);
    }

    private int calculateTotalScore(final List<Card> cards) {
        int countOfAce = countAceCard(cards);
        int score = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return changeAce(score, countOfAce);
    }

    private int countAceCard(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int changeAce(int score, int countOfAce) {
        while (canChangeAce11to1(score, countOfAce)) {
            score = score - DIFFERENCE_IN_CHANGE_OF_ACE;
            countOfAce--;
        }
        return score;
    }

    private boolean canChangeAce11to1(final int score, final int countOfAce) {
        return score > 21 && countOfAce > 0;
    }

    public int getValue() {
        return value;
    }

    public boolean isLessThen21() {
        return value < 21;
    }

    public boolean isBiggerThen21() {
        return value > 21;
    }

    public boolean is21() {
        return value == 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
