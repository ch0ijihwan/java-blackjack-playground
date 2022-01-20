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

class BustTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.JACK), PlayingCard.of(Suit.CLUB, Number.JACK),
                PlayingCard.of(Suit.HEART, Number.TWO)));
    }

    @Test
    @DisplayName("draw() 호출 시, 게임이 끝난 상태임을 알리는 예외처리를 반환한다.")
    void draw() {
        //given 
        State state = new Bust(cards);

        //then
        assertThatThrownBy(() -> state.draw(PlayingCard.of(Suit.CLUB, Number.TWO))).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }

    @Test
    @DisplayName("stay() 호출 시, 게임이 끝난 상태임을 알리는 예외처리를 반환한다.")
    void stay() {
        //given
        State state = new Bust(cards);

        //then
        assertThatThrownBy(state::stay).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝난 상태입니다.");
    }

    @Test
    @DisplayName("isFinished() 호출 시, true 를 반환한다.")
    void isFinished() {
        //given
        State state = new Bust(cards);

        //when
        boolean actual = state.isFinished();

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("getProfit() 호출 시, 딜러카드가 버스트이먄 0을 반환, 그렇지 않은 경우에는 배팅 금액에 -1 을 곱한 값으 반환한다.")
    @MethodSource("dealerCardsAndExpectedProfitProvider")
    void getProfit(Cards dealerCards, int expectedProfit) {
        //given
        State state = new Bust(cards);
        BattingMoney inputtedBattingMoney = new BattingMoney(10);

        //when
        int actualProfit = state.getProfit(inputtedBattingMoney, dealerCards);
        //then
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> dealerCardsAndExpectedProfitProvider() {
        return Stream.of(
                Arguments.of(new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.JACK), PlayingCard.of(Suit.CLUB, Number.JACK),
                        PlayingCard.of(Suit.CLUB, Number.THREE))), 0),
                Arguments.of(new Cards(Arrays.asList(PlayingCard.of(Suit.CLUB, Number.JACK),
                        PlayingCard.of(Suit.CLUB, Number.JACK))), -10));
    }
}