package view.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DrawingCardAnswerTest {
    @Test
    @DisplayName("객체 생성 시, 입력 값이 n,y이 아니면 예외처리를 반환한다.")
    void createExceptionByWrongInput() {
        //given
        String input = "a";

        //then
        assertThatThrownBy(() -> DrawingCardAnswer.of(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 y 혹은 n 이어야만 합니다.");
    }
}
