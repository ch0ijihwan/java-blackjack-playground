package view.input;

import java.util.Scanner;

public class InputDisplay {
    private static final Scanner SCANNER  = new Scanner(System.in);

    public static String[] inputPlayerNames() {
        return SCANNER.nextLine().split(",");
    }

    public static int inputBattlingMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        return SCANNER.nextInt();
    }

    public static boolean inputDrawMoreCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return askYesOrNo(SCANNER.next());
    }

    private static boolean askYesOrNo(String value) {
        if (value.equals("y")) {
            return true;
        } else if (value.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n을 입력해야 합니다.");
    }
}
