package pos.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Order {

    private static final int DEFAULT_COUNT = 0;

    private final Map<Menu, Integer> order = new HashMap<>();

    public Order(List<Menu> menus) {
        Objects.requireNonNull(menus);
        for (Menu menu : menus) {
            order.put(menu, DEFAULT_COUNT);
        }
    }

    public boolean canAddOrder(Menu menu, int count) {
        if (count < 1) {
            return false;
        }
        return order.get(menu) + count < 100;
    }
}
