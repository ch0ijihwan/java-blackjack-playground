package model.participant.state.finished;

import model.card.Cards;
import model.card.vo.Number;
import model.card.vo.Card;
import model.card.vo.Suit;
import model.participant.state.State;
import model.participant.vo.BattingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StayTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE)));
    }

    @Test
    @DisplayName("draw() 호출 시, 게임이 끝난 상태임을 알리는 예외처리를 반환한다.")
    void draw() {
        //given
        State state = new Stay(cards);

        //then
        assertThatThrownBy(() -> state.draw(Card.of(Suit.CLUB, Number.TWO))).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }


    @Test
    @DisplayName("stay() 호출 시, 게임이 끝난 상태임을 알리는 예외처리를 반환한다.")
    void stay() {
        //given
        State state = new Stay(cards);

        //then
        assertThatThrownBy(state::stay).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }

    @Test
    @DisplayName("isFinished() 호출 시, true 를 반환한다.")
    void isFinished() {
        //given
        State state = new Stay(cards);

        //when
        boolean actual = state.isFinished();

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("getProfit 호출 시, 딜러의 카드의 합과 자신 카드의 합이 같으면 0 을 반환하고, " +
            "딜러가 블랙잭이거나 자신의 카드의 합이 딜러 카드의 합보다 작으면 배팅금액의 * -1한 값을 반환하고," +
            " 그렇지 않는다면(자신의 값이 딜러 보다 큰 경우와 딜러가 버스트인 경우) 배팅금액을 반환한다.  ")
    @MethodSource("dealerCardsAndExpectedProfitProvider")
    void getProfit(Cards dealerCards, int expectedProfit) {
        //given
        State state = new Stay(cards);
        BattingMoney inputtedBattingMoney = new BattingMoney(10);

        //when
        int actualProfit = state.getProfit(inputtedBattingMoney, dealerCards);

        //then
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> dealerCardsAndExpectedProfitProvider() {
        return Stream.of(
                Arguments.of(new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.NINE))), 0),
                Arguments.of(new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.ACE))), -10),
                Arguments.of(new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.JACK))), -10),
                Arguments.of(new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.EIGHT))), 10),
                Arguments.of(new Cards(Arrays.asList(Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.JACK),
                        Card.of(Suit.CLUB, Number.TWO))), 10));
    }
}