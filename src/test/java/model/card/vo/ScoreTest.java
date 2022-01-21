package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreTest {
    @ParameterizedTest
    @DisplayName("getValue() 호출 시, 카드 리스트를 받아 카드 의 총 스코어를 반환한다." +
            "이때 카드의 총 스코어가 21이하인 경우 가능한 한 ACE 를 11로 간주하고 계산한다.")
    @MethodSource("providerCardsAndExpectedScore")
    void getValue(final List<Card> cards, final int expect) {
        //given
        Score score = Score.of(cards);

        //when
        int actual = score.getValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerCardsAndExpectedScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.THREE), Card.of(Suit.SPADE, Number.FOUR)), 7),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.JACK)), 21),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.ACE), Card.of(Suit.SPADE, Number.ACE)), 13),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.JACK), Card.of(Suit.HEART, Number.FOUR)), 15));
    }

    @ParameterizedTest
    @DisplayName("isLessThan21() 호출 시, 스코어가 21 미만이면 true 를 반환한다.")
    @MethodSource("providerLessThen21CardsAndExpect")
    void isLessThen21(final List<Card> cards, final boolean expect) {
        //given
        Score score = Score.of(cards);

        //when
        boolean actual = score.isLessThen21();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerLessThen21CardsAndExpect() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.NINE)), true),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.JACK)), false));
    }

    @ParameterizedTest
    @DisplayName("isBiggerThan21() 호출 시, 스코어가 21를 초과하면 true 를 반환한다.")
    @MethodSource("providerBiggerThen21CardsAndExpect")
    void isBiggerThan21(final List<Card> cards, final boolean expect) {
        //given
        Score score = Score.of(cards);

        //when
        boolean actual = score.isBiggerThen21();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerBiggerThen21CardsAndExpect() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.JACK), Card.of(Suit.SPADE, Number.JACK),
                        Card.of(Suit.SPADE, Number.TWO)), true),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.JACK)), false)
        );
    }

    @ParameterizedTest
    @DisplayName("is21() 호출 시, 스코어가 21이면 true 를 반환한다.")
    @MethodSource("providerIS21CardsAndExpect")
    void is21(final List<Card> cards, final boolean expect) {
        //given
        Score score = Score.of(cards);

        //when
        boolean actual = score.is21();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerIS21CardsAndExpect() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.NINE)), false),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.ACE), Card.of(Suit.SPADE, Number.JACK)), true),
                Arguments.of(Arrays.asList(
                        Card.of(Suit.HEART, Number.JACK), Card.of(Suit.SPADE, Number.JACK),
                        Card.of(Suit.SPADE, Number.TWO)), false));
    }
}