package model.participant;

import model.card.Cards;
import model.card.vo.Number;
import model.card.vo.PlayingCard;
import model.card.vo.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static model.card.vo.PlayingCard.makeCard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {
    @ParameterizedTest
    @DisplayName("isScoreLessThan16() 호출 시, 카드가 16 이하이면 true 를 반환한다.")
    @CsvSource(value = {"NINE:true", "JACK:false"},delimiter = ':')
    void setUpStartingCards(final Number inputtedNumber,final boolean expect) {
        //given
        Dealer dealer = new Dealer(List.of(makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, inputtedNumber)));

        //when
        boolean actual = dealer.isScoreLessThan16();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("draw() 호출 시, 카드 한장을 받아 리스트에 추가한다.")
    void draw() {
        //given
        Dealer dealer = new Dealer(List.of(makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, Number.TWO)));
        PlayingCard inputtedCard = makeCard(Suit.SPADE, Number.KING);
        List<PlayingCard> expect = List.of(
                makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, Number.TWO), makeCard(Suit.SPADE, Number.KING)
        );

        //when
        dealer.draw(inputtedCard);

        //then
        assertThat(dealer).isEqualTo(new Dealer(expect));
    }

    @Test
    @DisplayName("getScore() 호출 시, 딜러의 스코어를 반환한다.")
    void getScore() {
        //given
        Dealer dealer = new Dealer(List.of(makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, Number.TWO)));
        int expectedScore = 9;

        //when
        int actual = dealer.getScore();

        //then
        assertThat(actual).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("getCards() 호출 시, 카드를 모두 반환한다.")
    void getCards() {
        //given
        Dealer dealer = new Dealer(List.of(makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, Number.TWO)));
        Cards expect = new Cards(List.of(makeCard(Suit.HEART, Number.SEVEN), makeCard(Suit.HEART, Number.TWO)));

        //when
        Cards actualCards = dealer.getCards();

        //then
        assertThat(actualCards).isEqualTo(expect);
    }
}
