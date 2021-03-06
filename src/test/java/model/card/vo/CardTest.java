package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {
    @Test
    @DisplayName("getNumberValue() 호출 시, 카드 객체의 Number 값을 반환한다.")
    void getNumberValue() {
        //given
        Number inputtedNumber = Number.ACE;
        Suit inputtedSuit = Suit.HEART;
        int expectedNumber = 11;
        Card card = Card.of(inputtedSuit, inputtedNumber);

        //when
        int actualNumber = card.getNumberValue();

        //then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("getNumberSuit() 호출 시, 카드 객체의 Suit 값을 반환한다.")
    void getNumberSuit() {
        //given
        Number inputtedNumber = Number.ACE;
        Suit inputtedSuit = Suit.HEART;
        String expectedSuit = "Heart";
        Card card = Card.of(inputtedSuit, inputtedNumber);

        //when
        String actualSuit = card.getSuitValue();

        //then
        assertThat(actualSuit).isEqualTo(expectedSuit);
    }

    @ParameterizedTest
    @DisplayName("isAce() 호출 시, 카드의 Number 가 ACE 라면 true 를 반환한다.")
    @CsvSource(value = {"ACE:true", "TWO:false"}, delimiter = ':')
    void isAce(Number inputtedNumber, boolean expected) {
        //given
        Suit inputtedSuit = Suit.HEART;
        Card card = Card.of(inputtedSuit, inputtedNumber);

        //when
        boolean actual = card.isAce();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}