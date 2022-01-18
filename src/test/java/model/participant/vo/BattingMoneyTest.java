package model.participant.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BattingMoneyTest {
    @Test
    @DisplayName("객체 생성 시, 배팅 금액을 입력 받고 이를 저장한다.")
    void createBattingMoney() {
        //given
        int inputtedMoney = 10000;

        //when
        BattingMoney battingMoney = new BattingMoney(inputtedMoney);

        //then
        assertThat(battingMoney).isEqualTo(new BattingMoney(inputtedMoney));
    }

    @DisplayName("객체 생성 시, 파라미터로 부터 입력 받은 값이 양수가 아니면 예외처리를 반환한다.")
    void validateBattingMoney() {
        //given
        int inputtedNumber = -1;

        //then
        assertThatThrownBy(() -> new BattingMoney(inputtedNumber)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수여야 합니다.");
    }

    @Test
    @DisplayName("getValue 호출 시, 저장 된 값을 반환한다.")
    void getValue() {
        //given
        int inputtedNumber = 1000;
        int expect = 1000;
        BattingMoney battingMoney = new BattingMoney(inputtedNumber);

        //when
        int actual = battingMoney.getValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
