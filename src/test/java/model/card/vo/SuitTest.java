package model.card.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SuitTest {
    @Test
    @DisplayName("getValue() 호출 시, enum 이 가지고 있는 값을 반환한다.")
    void getValue() {
        //given
        Suit suit = Suit.HEART;
        String expectedSuit = "heart";

        //when
        String actual = suit.getValue();

        //then
        assertThat(actual).isEqualTo(expectedSuit);
    }
}