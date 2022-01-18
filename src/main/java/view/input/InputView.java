package view.input;

public interface InputView {
    String[] inputPlayerNames();

    int inputBattlingMoney(final String name);

    DrawingCardAnswer inputDrawMoreCard(final String name);
}
