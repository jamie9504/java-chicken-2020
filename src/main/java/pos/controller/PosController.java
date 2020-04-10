package pos.controller;

import java.util.List;
import pos.domain.element.Menu;
import pos.domain.element.MenuRepository;
import pos.domain.element.Table;
import pos.domain.element.TableRepository;
import pos.view.InputView;
import pos.view.OutputView;

public class PosController {
    public static void run() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();

        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }
}
