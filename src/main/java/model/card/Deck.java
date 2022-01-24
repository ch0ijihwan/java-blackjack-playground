package model.card;

import model.card.vo.Card;
import model.card.vo.Number;
import model.card.vo.Suit;

import java.util.*;
import java.util.stream.Collectors;

import static model.card.vo.Card.of;

public class Deck {
    private static final int FIRST_INDEX = 0;
    private final List<Card> cards;

    public Deck() {
        List<List<Card>> cardsListBySuit = Arrays.stream(Suit.values())
                .map(this::createNumberCards)
                .collect(Collectors.toUnmodifiableList());
        cards = cardsListBySuit.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
    }

    private List<Card> createNumberCards(final Suit suit) {
        return Arrays.stream(Number.values())
                .map(number -> of(suit, number))
                .collect(Collectors.toUnmodifiableList());
    }

    public Card provideCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없는 상태입니다. 카드를 뽑을 수 없습니다.");
        }
        return cards.remove(FIRST_INDEX);
    }

    public Cards startingDraw() {
        return new Cards(new ArrayList<>(Arrays.asList(provideCard(), provideCard())));
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
