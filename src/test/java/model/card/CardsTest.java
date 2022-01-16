package model.card;

import model.card.vo.Number;
import model.card.vo.PlayingCard;
import model.card.vo.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.card.vo.PlayingCard.makeCard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardsTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        List<PlayingCard> initialCards
                = new ArrayList<>(List.of(makeCard(Suit.SPADE, Number.ACE), makeCard(Suit.HEART, Number.SEVEN)));
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
    @DisplayName("getScore() 호출 시, 카드의 총 스코어 값을 반환한다." +
            "이때 총 스코어가 21 보다 큰 경우, 소지한 ACE 카드의 횟수 만큼 ACE의 값(11)을 1로 바꾸어 계산한다.")
    @CsvSource(value = {"TWO:20", "FOUR:12"}, delimiter = ':')
    void calculateTotalScore(Number inputtedNumber, int expect) {
        //given
        cards.add(makeCard(Suit.SPADE, inputtedNumber));

        //when
        int actual = cards.getScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("isOver21() 호출 시, 카드의 총 스코어가 21을 초과하면, true 를 반환한다.")
    @CsvSource(value = {"THREE:true", "TWO:false"}, delimiter = ':')
    void isOver21(Number inputtedNumber, boolean expect) {
        //given
        List<PlayingCard> initialCards
                = new ArrayList<>(List.of(makeCard(Suit.SPADE, Number.JACK), makeCard(Suit.HEART, Number.NINE)));
        cards = new Cards(initialCards);
        cards.add(makeCard(Suit.SPADE, inputtedNumber));

        //when
        boolean actual = cards.isBurst();

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

    @Test
    @DisplayName("getCards() 호출 시, 카드들을 반환한다..")
    void getCards() {
        //given
        List<PlayingCard> expectedCards = List.of(makeCard(Suit.SPADE, Number.ACE), makeCard(Suit.HEART, Number.SEVEN));
        //when
        List<PlayingCard> actual = cards.getCards();

        //then
        assertThat(actual).isEqualTo(expectedCards);
    }
}