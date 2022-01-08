package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {
    @Test
    @DisplayName("getNumber() 호출 시, 카드 객체의 Number 값을 반환한다.")
    void getNumber() {
        //given
        Number inputtedNumber = Number.ACE;
        Suit inputtedSuit = Suit.HEART;
        Number expectedNumber = Number.ACE;
        Card card = Card.makeCard(inputtedSuit, inputtedNumber);
        //when
        Number actualNumber = card.getNumber();

        //then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @ParameterizedTest
    @DisplayName("isAce() 호출 시, 카드의 Number 가 ACE 라면 true 를 반환한다.")
    @CsvSource(value = {"ACE:true", "TWO:false"}, delimiter = ':')
    void isAce(Number inputtedNumber, boolean expected) {
        //given
        Suit inputtedSuit = Suit.HEART;
        Card card = Card.makeCard(inputtedSuit, inputtedNumber);

        //when
        boolean actual = card.isAce();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}