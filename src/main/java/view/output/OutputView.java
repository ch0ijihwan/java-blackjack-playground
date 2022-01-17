package view.output;

import java.util.List;

public interface OutputView {
    void showMessageStartingDraw(final List<String> names);

    void showPlayerCardStatus(final String name, final String cardStatus);

    void showMessageDealerWasDrawing();

    void showCardsResult(final String name, final String cardStatus, final int score);

    void showResultOfDividends(final String name, int dividends);
}
