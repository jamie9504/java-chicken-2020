package pos.controller;

import java.util.Arrays;
import pos.domain.Order;
import pos.domain.PaymentType;
import pos.domain.PosMenu;
import pos.domain.PosStatus;
import pos.domain.element.MenuRepository;
import pos.domain.element.TableRepository;
import pos.view.InputView;
import pos.view.OutputView;

public class PosController {

    public static void run() {
        final PosStatus posStatus
            = new PosStatus(TableRepository.tables(), new Order(MenuRepository.menus()));
        boolean repeat = true;
        while (repeat) {
            repeat = proceed(posStatus).isRepeat();
        }
    }

    private static PosMenu proceed(PosStatus posStatus) {
        OutputView.printPosMenu(Arrays.asList(PosMenu.values()));
        PosMenu posMenu = getPostMenu();
        if (posMenu == PosMenu.ORDER) {
            order(posStatus);
        }
        if (posMenu == PosMenu.PAYMENT) {
            payment(posStatus);
        }
        if (posMenu == PosMenu.END) {
            OutputView.printTermination();
        }
        return posMenu;
    }

    private static PosMenu getPostMenu() {
        while (true) {
            try {
                return PosMenu.of(Integer.parseInt(InputView.inputPosMenuNumber()));
            } catch (Exception e) {
                OutputView.printPostMenuException();
            }
        }
    }

    private static void order(PosStatus posStatus) {
        OutputView.printTables(posStatus.getTables());
        int tableNo = getTableNo(posStatus);
        OutputView.printMenus(posStatus.getMenus(tableNo));
        int menuNo = getMenuNo(posStatus, tableNo);
        addMenuCount(posStatus, tableNo, menuNo);
    }

    private static void payment(PosStatus posStatus) {
        OutputView.printTables(posStatus.getTables());
        int tableNo = getTableNo(posStatus);
        OutputView.printOrder(posStatus.getOrder(tableNo));

        PaymentType paymentType = getPaymentType(tableNo);
        OutputView.printPrice(posStatus.getPrice(tableNo, paymentType));
        posStatus.clearOrder(tableNo, new Order(MenuRepository.menus()));
    }

    private static int getTableNo(PosStatus posStatus) {
        while (true) {
            try {
                int tableNo = Integer.parseInt(InputView.inputTableNumber());
                if (posStatus.containsTable(tableNo)) {
                    return tableNo;
                }
                OutputView.printTableException();
            } catch (NumberFormatException e) {
                OutputView.printNumberException();
            } catch (Exception e) {
                OutputView.printTableException();
            }
        }
    }

    private static int getMenuNo(PosStatus posStatus, int tableNo) {
        while (true) {
            try {
                int menuNo = Integer.parseInt(InputView.inputMenuNumber());
                if (posStatus.containsMenu(menuNo, tableNo)) {
                    return menuNo;
                }
                OutputView.printMenuException();
            } catch (NumberFormatException e) {
                OutputView.printNumberException();
            } catch (Exception e) {
                OutputView.printMenuException();
            }
        }
    }

    private static void addMenuCount(PosStatus posStatus, int tableNo, int menuNo) {
        while (true) {
            try {
                int menuCount = Integer.parseInt(InputView.inputMenuCount());
                if (posStatus.canAddMenuCount(tableNo, menuNo, menuCount)) {
                    posStatus.addMoveCount(tableNo, menuNo, menuCount);
                    return;
                }
            } catch (NumberFormatException e) {
                OutputView.printNumberException();
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }

    private static PaymentType getPaymentType(int tableNo) {
        while (true) {
            try {
                int paymentType = Integer.parseInt(InputView.inputPaymentType(tableNo));
                return PaymentType.of(paymentType);
            } catch (NumberFormatException e) {
                OutputView.printNumberException();
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }
}
