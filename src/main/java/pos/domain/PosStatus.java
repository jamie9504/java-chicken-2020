package pos.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import pos.domain.element.Menu;
import pos.domain.element.Table;

public class PosStatus {

    private final Map<Table, Order> posStatus = new HashMap<>();

    public PosStatus(List<Table> tables, Order order) {
        Objects.requireNonNull(tables, "tables 파라미터 - Null 비허용");
        Objects.requireNonNull(order, "order 파라미터 - Null 비허용");
        for (Table table : tables) {
            posStatus.put(table, order.getCopyMenuInstance());
        }
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(posStatus.keySet().stream()
            .sorted(Comparator.comparingInt(Table::getNumber))
            .collect(Collectors.toList()));
    }

    public Order getOrder(int tableNo) {
        return getOrder(getTable(tableNo));
    }

    public Order getOrder(Table table) {
        return posStatus.get(table);
    }

    public List<Menu> getMenus(int tableNo) {
        Table table = getTable(tableNo);
        return posStatus.get(table).getMenus();
    }

    private Table getTable(int tableNo) {
        return posStatus.keySet().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("테이블 번호가 없습니다."));
    }

    public boolean containsTable(int tableNo) {
        return posStatus.keySet().stream()
            .anyMatch(table -> table.isSameNo(tableNo));
    }

    public boolean containsMenu(int menuNo, int tableNo) {
        return posStatus.keySet().stream()
            .filter(table -> table.isSameNo(tableNo))
            .anyMatch(table -> posStatus.get(table).containsMenu(menuNo));
    }

    public boolean canAddMenuCount(int tableNo, int menuNo, int menuCount) {
        return posStatus.get(getTable(tableNo)).canAddOrder(menuNo, menuCount);
    }

    public void addMoveCount(int tableNo, int menuNo, int menuCount) {
        posStatus.get(getTable(tableNo)).addOrder(menuNo, menuCount);
    }

    public Price getPrice(int tableNo, PaymentType paymentType) {
        return posStatus.get(getTable(tableNo)).getPrice(paymentType);
    }

    public void clearOrder(int tableNo, Order order) {
        posStatus.put(getTable(tableNo), order);
    }
}
