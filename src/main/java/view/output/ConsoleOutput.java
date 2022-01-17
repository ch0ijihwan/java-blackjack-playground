package view.output;

import java.util.List;

public class ConsoleOutput implements OutputView {
    @Override
    public void showMessageStartingDraw(final List<String> names) {
        System.out.println("딜러와" + names + "에게 2장의 카드를 나누었습니다.");
    }

    @Override
    public void showPlayerCardStatus(final String name, final String cardStatus) {
        System.out.println(name + "의 카드" + cardStatus);
    }

    @Override
    public void showMessageDealerWasDrawing() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    @Override
    public void showCardsResult(final String name, final String cardStatus, final int score) {
        System.out.println(name + "카드: " + cardStatus + "- 결과: " + score);
    }

    @Override
    public void showResultOfDividends(String name, int dividends) {
        System.out.println(name + ":" + dividends);
    }
}
