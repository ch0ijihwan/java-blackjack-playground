package view.input;

import java.util.Scanner;

public class ConsoleInput implements InputView {
    private final Scanner scanner;

    public ConsoleInput(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String[] inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine().split(",");
    }

    @Override
    public int inputBattlingMoney(final String name) {
        System.out.println(name + "의 배팅 금액은?");
        return scanner.nextInt();
    }

    @Override
    public boolean inputDrawMoreCard(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return askYesOrNo(scanner.next());
    }

    private boolean askYesOrNo(final String value) {
        if (value.equals("y")) {
            return true;
        } else if (value.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n을 입력해야 합니다.");
    }
}
