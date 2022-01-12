package model;

import model.vo.Number;
import model.vo.PlayingCard;
import model.vo.Suit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static model.vo.PlayingCard.makeCard;

public class Deck {
    private static final int FIRST_INDEX = 0;

    private final List<PlayingCard> cards;

    public Deck() {
        List<List<PlayingCard>> cardsListBySuit = Arrays.stream(Suit.values())
                .map(this::createNumberCards)
                .collect(Collectors.toUnmodifiableList());
        cards = cardsListBySuit.stream().flatMap(Collection::stream)
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
}
