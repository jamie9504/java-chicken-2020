package pos.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
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

    public Order getCopyMenuInstance() {
        return new Order(new ArrayList<>(order.keySet()));
    }

    public boolean canAddOrder(int menuId, int count) {
        return canAddOrder(getMenu(menuId), count);
    }

    public boolean canAddOrder(Menu menu, int count) {
        if (!order.containsKey(menu) || count < 1) {
            return false;
        }
        return order.get(menu) + count < 100;
    }

    private Menu getMenu(int menuId) {
        return order.keySet().stream()
            .filter(menu -> menu.sameNumber(menuId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("없는 MENU ID 입니다."));
    }

    public void addOrder(int menuId, int count) {
        addOrder(getMenu(menuId), count);
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

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(order.keySet().stream()
            .sorted(Comparator.comparingInt(Menu::getNumber))
            .collect(Collectors.toList()));
    }

    public int getCount(Menu menu) {
        return order.get(menu);
    }

    public boolean containsMenu(int menuNo) {
        return order.keySet().stream()
            .anyMatch(menu -> menu.sameNumber(menuNo));
    }

    public Price getPrice(PaymentType paymentType) {
        int sumPrice = order.keySet().stream()
            .mapToInt(menu -> menu.getPrice() * order.get(menu))
            .sum();
        int sumChicken = (int) order.keySet().stream()
            .filter(Menu::isChicken)
            .count();
        return new Price(sumPrice, sumChicken, paymentType);
    }
}
