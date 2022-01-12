package model;

import model.vo.PlayingCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {
    @Test
    @DisplayName("객체 생성 시, 카드를 52장 생성한다.")
    void createDeck() {
        //given
        Deck deck;
        int expectedTotalNumberOfCards = 52;

        //when
        deck = new Deck();

        //then
        assertThat(deck).extracting("cards")
                .asList()
                .size()
                .isEqualTo(expectedTotalNumberOfCards);

    }

    @Test
    @DisplayName("draw() 호출 시, 덱에서 카드 한장을 뽑아 카드 객체를 반환한다.")
    void draw() {
        //given
        Deck deck = new Deck();
        int expectedNumberOfCards = 51;

        //when
        PlayingCard card = deck.draw();

        //then
        assertThat(deck).extracting("cards")
                .asList()
                .size()
                .isEqualTo(expectedNumberOfCards);
    }

    @Test
    @DisplayName("startingDraw() 호출 시, 카드 리스트에서 카드 두장을 반환한다.")
    void startingDraw() {
        //given
        Deck deck = new Deck();
        int expectedNumberOfCards = 2;

        //when
        List<PlayingCard> actualStartingCards = deck.startingDraw();

        //then
        assertThat(actualStartingCards.size()).isEqualTo(expectedNumberOfCards);
    }
}
