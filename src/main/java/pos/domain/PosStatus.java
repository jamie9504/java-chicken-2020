package pos.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PosStatus {

    private final Map<Table, Order> posStatus = new HashMap<>();

    public PosStatus(List<Table> tables, Order order) {
        Objects.requireNonNull(tables, "tables 파라미터 - Null 비허용");
        Objects.requireNonNull(order, "order 파라미터 - Null 비허용");
        for (Table table : tables) {
            posStatus.put(table, order.getOrderCopyMenu());
        }
    }
}
