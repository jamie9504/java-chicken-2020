package pos.controller;

import java.util.List;
import pos.domain.Menu;
import pos.domain.MenuRepository;
import pos.domain.Table;
import pos.domain.TableRepository;
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
