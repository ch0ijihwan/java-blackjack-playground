package view.output;

import java.util.List;

public class OutputDisplay {
    public static void showMessageAskNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void showMessageStartingDraw(List<String> names) {
        System.out.println("딜러와" + names + "에게 2장의 카드를 나누었습니다.");
    }

    public static void showDealerOneCard(String cardStatus) {
        System.out.println("딜러의 카드" + cardStatus);
    }

    public static void showPlayerCardStatus(String name, String cardStatus) {
        System.out.println(name + "의 카드" + cardStatus);
    }

    public static void showMessageAskOneDraw(String name) {
        System.out.println(name + "는 한장의 카드를 더 뽑으시겠습니까?(예는 y 아니오는 n");
    }

    public static void showMessageDealerWasDrawing() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResultOfDealerCards(String cardStatus, int score) {
        System.out.println("딜러카드: " + cardStatus + "- 결과: " + score);
    }

    public static void showCardsResult(String name, String cardStatus, int score) {
        System.out.println(name + "카드: " + cardStatus + "- 결과: " + score);
    }

    public static void showResultOfDividends(String name, int dividends) {
        System.out.println(name+":"+dividends);;
    }

    public static void showResultOfDealerDividends(int dividends){
        System.out.println("딜러:"+dividends);
    }
}
