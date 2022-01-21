package model.participant.state.running;

import model.card.Cards;
import model.card.vo.Number;
import model.card.vo.Card;
import model.card.vo.Suit;
import model.participant.state.State;
import model.participant.state.finished.Bust;
import model.participant.state.finished.Stay;
import model.participant.vo.BattingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class HitTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(new ArrayList<>(Arrays.asList(Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE))));
    }

    @Test
    @DisplayName("draw() 호출 시, 카드를 추가하였을 때 카드가 Bust 가 되면 갖고 있는 카드들로 Bust 객체를 만들어 반환한다.")
    void createBustWhenDraw() {
        //given
        State state = new Hit(cards);
        Card inputCard = Card.of(Suit.CLUB, Number.THREE);
        Cards expectedCards = new Cards(Arrays.asList(
                Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE),
                Card.of(Suit.CLUB, Number.THREE)));

        //when
        State actualState = state.draw(inputCard);

        //then
        assertThat(actualState).isEqualTo(new Bust(expectedCards));
    }

    @Test
    @DisplayName("draw() 호출 시, 카드를 추가하였을 때 카드가 Bust 가 되지 않는다면 갖고 있는 카드들로 새로운 Hit 객체를 만들어 반환한다.")
    void createHitWhenDraw() {
        //given
        State state = new Hit(cards);
        Card inputCard = Card.of(Suit.CLUB, Number.TWO);
        Cards expectedCards = new Cards(Arrays.asList(
                Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE),
                Card.of(Suit.CLUB, Number.TWO)));

        //when
        State actualState = state.draw(inputCard);

        //then
        assertThat(actualState).isEqualTo(new Hit(expectedCards));
    }

    @Test
    @DisplayName("stay() 호출 시, 갖고 있는 카드들로 새로운 Stay 객체를 만들어 반환한다.")
    void stay() {
        //given
        State state = new Hit(cards);
        Cards expectedCards = new Cards(Arrays.asList(
                Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE)));

        //when
        State actualState = state.stay();

        //then
        assertThat(actualState).isEqualTo(new Stay(expectedCards));
    }

    @Test
    @DisplayName("isFinished() 호출 시, false 를 반환한다.")
    void isFinished() {
        //given
        State state = new Hit(cards);

        //when
        boolean actual = state.isFinished();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("getProfit() 호출 시, 예외처리를 반환한다. ")
    void getProfit() {
        //given
        State state = new Hit(cards);
        BattingMoney battingMoney = new BattingMoney(1000);
        Cards dealerCards = new Cards(Arrays.asList(
                Card.of(Suit.CLUB, Number.JACK), Card.of(Suit.CLUB, Number.NINE)));
        //then
        assertThatThrownBy(() -> state.getProfit(battingMoney, dealerCards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Running(카드를 뽑는 중) 상태이기 때문에, 아직 이익금을 알 수 없습니다.");
    }
}