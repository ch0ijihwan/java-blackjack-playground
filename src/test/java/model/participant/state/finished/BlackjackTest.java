package model.participant.state.finished;

import model.card.Cards;
import model.card.vo.Number;
import model.card.vo.PlayingCard;
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

class BlackjackTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.ACE), PlayingCard.of(Suit.CLUB, Number.JACK)));
    }

    @ParameterizedTest
    @DisplayName("getProfit() 호출 시, 딜러가 블랙잭이면 0을 반환하고, 그렇지 않는다면 배당금의 1.5를 곱한 값을 반환한다")
    @MethodSource("dealerCardsAndExpectedProfitProvider")
    void getProfit(Cards cards, int expectedProfit) {
        //given
        State state = new Blackjack(cards);
        int inputtedBattleMoney = 10;

        //when
        int actual = state.getProfit(new BattingMoney(inputtedBattleMoney), cards);

        //then
        assertThat(actual).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> dealerCardsAndExpectedProfitProvider() {
        return Stream.of(
                Arguments.of(
                        new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.ACE), PlayingCard.of(Suit.CLUB, Number.JACK))), 15),
                Arguments.of(
                        new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.JACK), PlayingCard.of(Suit.CLUB, Number.JACK))), 0
                ));
    }

    @Test
    @DisplayName("isFinished() 호출 시, true 를 반환한다.")
    void isFinished() {
        //given
        State state = new Blackjack(cards);

        //when
        boolean actual = state.isFinished();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("stay() 호출 시, 예외처리를 반환한다.")
    void stay() {
        //given
        State state = new Blackjack(cards);

        //then
        assertThatThrownBy(state::stay).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }

    @Test
    @DisplayName("draw() 호출 시, 예외처리를 반환한다.")
    void draw() {
        //given
        State state = new Blackjack(cards);

        //then
        assertThatThrownBy(() -> state.draw(PlayingCard.of(Suit.CLUB, Number.THREE))).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }
}
