package view.input;

public interface InputView {
    String[] inputPlayerNames();

    int inputBattlingMoney(final String name);

    boolean inputDrawMoreCard(final String name);
}
