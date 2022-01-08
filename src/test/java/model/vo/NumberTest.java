package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NumberTest {
    @ParameterizedTest
    @DisplayName("isAce() 호출 시, enum 이 Ace 라면 true 를 반환한다.")
    @CsvSource(value = {"ACE:true", "TWO:false"}, delimiter = ':')
    void isAce(String value, boolean expected) {
        //given
        Number number = Number.valueOf(value);

        //when
        boolean actual = number.isAce();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("isJQK() 호출 시, enum 이 JACK, Queen, King 이라면 true 를 반환한다.")
    @ValueSource(strings = {"JACK", "QUEEN", "KING"})
    void isJQK(String value) {
        //given
        Number number = Number.valueOf(value);

        //when
        boolean actual = number.isJQK();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("getValue 호출 시, enum 의 값을 반환한다.")
    void getValue() {
        //given
        Number number = Number.ACE;
        int expectedNumber = 1;

        //when
        int actualNumber = number.getValue();

        //then
        assertThat(actualNumber).isEqualTo(expectedNumber );
    }
}