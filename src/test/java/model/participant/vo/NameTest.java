package model.participant.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {
    @Test
    @DisplayName("객체 생성 시, 파라미터로 부터 입력받은 이름이 없을 경우 예외처리를 반환한다.")
    void createName() {
        //given
        String inputtedName = "";

        //then
        assertThatThrownBy(() -> new Name(inputtedName)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("getValue() 호출 시, 저장되어 있는 값을 반환한다.")
    void getValue() {
        //given
        String inputtedName = "apple";
        String expectedName = "apple";
        Name name = new Name(inputtedName);

        //when
        String actual = name.getValue();

        //then
        assertThat(actual).isEqualTo(expectedName);
    }
}
