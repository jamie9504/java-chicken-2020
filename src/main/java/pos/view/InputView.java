package pos.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return SCANNER.nextLine();
    }

    public static String inputPosMenuNumber() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return SCANNER.nextLine();
    }

    public static String inputMenuNumber() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        return SCANNER.nextLine();
    }

    public static String inputMenuCount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return SCANNER.nextLine();
    }

    public static String inputPaymentType(int tableId) {
        System.out.printf("## %d번 테이블의 결제를 진행합니다.%s", tableId, System.lineSeparator());
        System.out.println("## 신용 카드는 1번, 현금은 2번");
        return SCANNER.nextLine();
    }
}
