package pos.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        return Collections.unmodifiableList(new ArrayList<>(posStatus.keySet()));
    }

    public Order getOrder(int tableNo) {
        Table table = getTable(tableNo);
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

    private boolean containsTable(int tableNo) {
        return posStatus.keySet().stream()
            .anyMatch(table -> table.isSameNo(tableNo));
    }
}
