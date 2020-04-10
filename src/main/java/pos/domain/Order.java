package pos.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import pos.domain.element.Menu;

public class Order {

    private static final int DEFAULT_COUNT = 0;

    private final Map<Menu, Integer> order = new HashMap<>();

    public Order(List<Menu> menus) {
        Objects.requireNonNull(menus, "menus 파라미터 - Null 비허용");
        for (Menu menu : menus) {
            order.put(menu, DEFAULT_COUNT);
        }
    }

    public Order getOrderCopyMenu() {
        return new Order(new ArrayList<>(order.keySet()));
    }

    public boolean canAddOrder(Menu menu, int count) {
        if (!order.containsKey(menu) || count < 1) {
            return false;
        }
        return order.get(menu) + count < 100;
    }

    public void addOrder(Menu menu, int count) {
        if (canAddOrder(menu, count)) {
            order.put(menu, order.get(menu) + count);
            return;
        }
        throw new IllegalArgumentException("주문을 할 수 없습니다.");
    }

    public boolean isOrdered() {
        return order.values().stream()
            .mapToInt(count -> count)
            .sum() > 0;
    }
}
