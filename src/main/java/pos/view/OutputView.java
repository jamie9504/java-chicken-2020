package pos.view;

import java.util.List;
import pos.domain.Order;
import pos.domain.PosMenu;
import pos.domain.Price;
import pos.domain.element.Menu;
import pos.domain.element.Table;

public class OutputView {

    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println();
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printTopLine(size);
        printTableNumbers(tables);
        printBOTTOMLine(size, tables);
        System.out.println();
    }

    private static void printTopLine(final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(TOP_LINE);
        }
        System.out.println();
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
        System.out.println();
    }

    private static void printBOTTOMLine(final int count, List<Table> tables) {
        for (int index = 0; index < count; index++) {
            System.out.print(BOTTOM_LINE);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printPosMenu(List<PosMenu> posMenus) {
        StringBuilder posMenuPrint = new StringBuilder();
        posMenuPrint.append(System.lineSeparator());
        posMenuPrint.append("## 메인화면");
        posMenuPrint.append(System.lineSeparator());
        for (PosMenu posMenu : posMenus) {
            posMenuPrint.append(posMenu.getNumber());
            posMenuPrint.append(" - ");
            posMenuPrint.append(posMenu.getName());
            posMenuPrint.append(System.lineSeparator());
        }
        System.out.println(posMenuPrint);
    }

    public static void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printTermination() {
        System.out.println("POS 시스템을 종료합니다.");
    }

    public static void printPostMenuException() {
        System.out.println("POS 메뉴를 숫자로 정확히 입력해주세요.");
    }

    public static void printTableException() {
        System.out.println("테이블 번호를 숫자로 정확히 입력해주세요.");
    }

    public static void printPrice(Price price) {
        System.out.println("##최종 결제할 금액");
        System.out.printf("%d원%s", price.getPrice(), System.lineSeparator());
    }

    public static void printOrder(Order order) {
        StringBuilder print = new StringBuilder();
        print.append("## 주문내역");
        print.append(System.lineSeparator());
        print.append("메뉴 수량 금액");
        for (Menu menu : order.getMenus()) {
            if (order.getCount(menu) == 0) {
                continue;
            }
            print.append(menu.getName());
            print.append(" ");
            print.append(order.getCount(menu));
            print.append(" ");
            print.append(menu.getPrice());
            print.append(System.lineSeparator());
        }
        System.out.println(print);
    }

    public static void printMenuException() {
        System.out.println("메뉴 번호를 숫자로 정확히 입력해주세요.");
    }

    public static void printNumberException() {
        System.out.println("숫자로 입력해주셔야 합니다.");
    }
}
