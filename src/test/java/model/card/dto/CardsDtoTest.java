package model.card.dto;

import model.card.Cards;
import model.card.vo.Card;
import model.card.vo.Number;
import model.card.vo.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardsDtoTest {
    private final Cards cards = new Cards(List.of(
            Card.of(Suit.HEART, Number.TWO), Card.of(Suit.CLUB, Number.THREE), Card.of(Suit.CLUB, Number.FOUR)));

    @Test
    @DisplayName("getScore 호출 시, 카드들의 스코어를 반환한다.")
    void getCardNumbers() {
        //given
        CardsDto cardsDto = CardsDto.from(cards);
        int expectedScore = 9;

        //when
        int actual = cardsDto.getScore();

        //then
        assertThat(actual).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("getCardDtoList() 호출 시, 카드들의 각 Dto 리스트를 반환한다.")
    void getCardDtoList() {
        //given
        CardsDto cardsDto = CardsDto.from(cards);
        List<CardDto> expect = List.of(CardDto.from(Card.of(Suit.HEART, Number.TWO)),
                CardDto.from(Card.of(Suit.CLUB, Number.THREE)), CardDto.from(Card.of(Suit.CLUB, Number.FOUR))
        );

        //when
        List<CardDto> actual = cardsDto.getCardDtoList();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
