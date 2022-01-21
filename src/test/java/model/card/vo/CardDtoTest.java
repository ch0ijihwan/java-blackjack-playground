package model.card.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDtoTest {
    private final Card card = Card.of(Suit.SPADE, Number.SEVEN);
    private CardDto cardDto;

    @BeforeEach
    void setUp() {
        cardDto = CardDto.from(card);
    }

    @Test
    @DisplayName("getCardNumber() 호출 시, 카드의 숫자를 반환한다.")
    void getCardNumber() {
        //given
        int expect = 7;

        //when
        int actual = cardDto.getCardNumber();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("getCardSuit() 호출 시, 카드의 무늬를 반환한다.")
    void getCardSuit() {
        //given
        String expect = "Spade";

        //when
        String actual = cardDto.getCardSuit();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
