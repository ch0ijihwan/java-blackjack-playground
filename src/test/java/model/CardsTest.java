package model;

import model.vo.Number;
import model.vo.PlayingCard;
import model.vo.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.vo.PlayingCard.makeCard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardsTest {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private Cards cards;

    @BeforeEach
    void setUp() {
        List<PlayingCard> initialCards = new ArrayList<>(List.of(makeCard(Suit.SPADE, Number.ACE), makeCard(Suit.HEART, Number.SEVEN)));
        cards = new Cards(initialCards);
    }

    @Test
    @DisplayName("add() 호출 시, 파라미터로 부터 받은 Card 객체를 Cards 리스트에 추가한다.")
    void add() {
        //given
        Suit inputtedSuit = Suit.SPADE;
        Number inputtedNumber = Number.TWO;

        List<PlayingCard> expectedCards = List.of(
                makeCard(Suit.SPADE, Number.ACE),
                makeCard(Suit.HEART, Number.SEVEN)
                , makeCard(inputtedSuit, inputtedNumber)
        );

        //when
        cards.add(makeCard(inputtedSuit, inputtedNumber));

        //then
        assertThat(cards).isEqualTo(new Cards(expectedCards));
    }

    @ParameterizedTest
    @DisplayName("hasAce() 호출 시, 카드 중 아직 1로 바꾸지 않은 Ace 가 있다면 true 을 반환한다.")
    @CsvSource(value = {"ACE:true", "TWO:false"}, delimiter = ':')
    void hasAce(Number inputtedNumber, boolean expect) {
        //given
        cards.add(makeCard(Suit.SPADE, inputtedNumber));
        cards.changeAceScore();

        //when
        boolean actual = cards.hasAce();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("changeAceScore() 호출 시, 카드 중 Ace 가 있다면 11로 계산 해주던 Ace 를 1로 바꾸어 계산한다." +
            "즉 전체 score 에서 10을 빼준다.")
    void changeAceScore() {
        //given
        int expectedScore = 8;

        //when
        cards.changeAceScore();

        //then
        assertThat(cards.getScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("getScore() 호출 시, 카드의 총 스코어 값을 반환한다.")
    void calculateTotalScore() {
        //given
        int expectedScore = 18;

        //when
        int actual = cards.getScore();

        //then
        assertThat(actual).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @DisplayName("isOver21() 호출 시, 카드의 총 스코어가 21을 초과하면 true 를 반환한다.")
    @CsvSource(value = {"FOUR:true", "TWO:false"}, delimiter = ':')
    void isOver21(Number inputtedNumber, boolean expect) {
        //given
        cards.add(makeCard(Suit.SPADE, inputtedNumber));

        //when
        boolean actual = cards.isOver21();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("isBlackJack() 호출 시, 카드의 스코어가 21이고, 카드의 개수가 2인 경우 true 를 반환한다.")
    @CsvSource(value = {"ACE,JACK:true", "ACE,SIX,FOUR:false", "ACE,TWO:false"}, delimiter = ':')
    void isBlackJack(String inputtedNumbers, boolean expect) {
        //given
        List<Number> numbers = Arrays.stream(inputtedNumbers.split(","))
                .map(Number::valueOf)
                .collect(Collectors.toUnmodifiableList());

        List<PlayingCard> initialCards = numbers.stream()
                .map(number -> makeCard(Suit.SPADE, number))
                .collect(Collectors.toUnmodifiableList());

        Cards cards = new Cards(initialCards);

        //when
        boolean actual = cards.isBlackJack();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
