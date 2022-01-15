package model.card;

import model.card.vo.Number;
import model.card.vo.PlayingCard;
import model.card.vo.Suit;

import java.util.*;
import java.util.stream.Collectors;

import static model.card.vo.PlayingCard.makeCard;

public class Deck {
    private static final int FIRST_INDEX = 0;

    private final List<PlayingCard> cards;

    public Deck() {
        List<List<PlayingCard>> cardsListBySuit = Arrays.stream(Suit.values())
                .map(this::createNumberCards)
                .collect(Collectors.toUnmodifiableList());
        cards = cardsListBySuit.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
    }

    private List<PlayingCard> createNumberCards(final Suit suit) {
        return Arrays.stream(Number.values())
                .map(number -> makeCard(suit, number))
                .collect(Collectors.toUnmodifiableList());
    }

    public PlayingCard draw() {
        return cards.remove(FIRST_INDEX);
    }

    public List<PlayingCard> startingDraw() {
        return List.of(draw(), draw());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
