package model.participant;

import model.card.Cards;
import model.card.vo.Number;
import model.card.vo.PlayingCard;
import model.card.vo.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static model.card.vo.PlayingCard.of;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        String inputtedName = "apple";
        int inputtedBattingMoney = 10000;
        Cards inputtedCards = new Cards(new ArrayList<>(
                Arrays.asList(of(Suit.SPADE, Number.ACE), of(Suit.HEART, Number.SEVEN))));
        player = new Player(inputtedName, inputtedBattingMoney, inputtedCards);
    }

    @Test
    @DisplayName("객체 생성 시, 파라미터로 부터 입력 받은 플레이어 이름, 배팅 금액, 스타팅 카드 2장을 저장한다.")
    void createPlayer() {
        //when
        String inputtedName = "apple";
        int inputtedBattingMoney = 10000;
        Cards inputtedCards = new Cards(List.of(of(Suit.SPADE, Number.ACE), of(Suit.HEART, Number.SEVEN)));

        //then
        assertThat(player).isEqualTo(new Player(inputtedName, inputtedBattingMoney, inputtedCards));

    }

    @Test
    @DisplayName("getCards() 실행 시, cards 를 반환한다.")
    void getCards() {
        //given
        List<PlayingCard> inputtedCards = List.of(of(Suit.SPADE, Number.ACE), of(Suit.HEART, Number.SEVEN));
        //when
        Cards actual = player.getCards();

        //then
        assertThat(actual).isEqualTo(new Cards(inputtedCards));
    }

    @Test
    @DisplayName("draw() 호출 시, 파라미터로부터 입력 받은 카드 한장을 카드 리스트에 추가한다.")
    void draw() {
        //given
        List<PlayingCard> expectedCards = Arrays.asList(
                of(Suit.SPADE, Number.ACE),
                of(Suit.HEART, Number.SEVEN),
                of(Suit.CLUB, Number.SEVEN)
        );

        //when
        player.draw(of(Suit.CLUB, Number.SEVEN));

        //then
        assertThat(player.getCards()).isEqualTo(new Cards(expectedCards));
    }

    @ParameterizedTest
    @DisplayName("getProfit() 호출 시, 파라미터로부터 입력 받은 카드리스트(딜러)와 본인(플레이어)의 카드를 비교한다." +
            "이 비교 결과에 따라 배당금을 반한다.")
    @MethodSource("dealerCardsAndExpectedDividendsProvider")
    void getProfit(final Cards dealerCards, final int expectedDividends) {
        //given
        player.stay();

        //when
        int actualDividends = player.getDividends(dealerCards);

        //then
        assertThat(actualDividends).isEqualTo(expectedDividends);
    }

    private static Stream<Arguments> dealerCardsAndExpectedDividendsProvider() {
        return Stream.of(
                Arguments.of(new Cards(List.of(of(Suit.HEART, Number.SIX), of(Suit.SPADE, Number.KING))), 10000),
                Arguments.of(new Cards(List.of(of(Suit.HEART, Number.NINE), of(Suit.SPADE, Number.KING))), -10000)
        );
    }
}