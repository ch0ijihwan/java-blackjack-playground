package model.card;

import model.card.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DeckTest {
    private static final int FIRST_INDEX_OF_DECK = 0;
    private static final int LAST_INDEX_OF_DECK = 52;

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
    @DisplayName("provideCard() 호출 시, 덱에서 카드 한장을 뽑아 카드 객체를 반환한다.")
    void provideCard() {
        //given
        Deck deck = new Deck();
        int expectedNumberOfCards = 51;

        //when
        Card card = deck.provideCard();

        //then
        assertThat(deck).extracting("cards")
                .asList()
                .size()
                .isEqualTo(expectedNumberOfCards);
    }

    @Test
    @DisplayName("provideCard() 호출 시, 덱에 카드가 존재하지 않는 다면 예외처리를 반환한ㄷ..")
    void provideCardWhenNoCard() {
        //given
        Deck deck = new Deck();
        IntStream.range(FIRST_INDEX_OF_DECK, LAST_INDEX_OF_DECK)
                .forEach(time -> deck.provideCard());

        //then
        assertThatThrownBy(deck::provideCard).isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 카드가 없는 상태입니다. 카드를 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("startingDraw() 호출 시, 카드 리스트에서 카드 두장을 반환한다.")
    void startingDraw() {
        //given
        Deck deck = new Deck();
        int expectedNumberOfCards = 2;

        //when
        Cards actualStartingCards = deck.startingDraw();

        //then
        assertThat(actualStartingCards.getPlayingCards().size()).isEqualTo(expectedNumberOfCards);
    }
}
