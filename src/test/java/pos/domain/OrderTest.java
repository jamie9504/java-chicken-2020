package pos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTest {

    @DisplayName("기본 생성 null 불가")
    @Test
    public void createOrder() {
        assertThatThrownBy(() -> new Order(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @DisplayName("수량을 더할 수 있는지 체크 - 양수만 가능, 도합 99개 초과 불가")
    @ParameterizedTest
    @CsvSource(value = {"0, 1", "100, 99"})
    public void canAddOrder(int failCount, int trueCount) {
        Menu trueMenu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
        Menu failMenu = new Menu(2, "후라이드", Category.CHICKEN, 16_000);
        Order order = new Order(Collections.singletonList(trueMenu));
        assertThat(order.canAddOrder(trueMenu, failCount)).isFalse();
        assertThat(order.canAddOrder(failMenu, trueCount)).isFalse();
        assertThat(order.canAddOrder(failMenu, failCount)).isFalse();
        assertThat(order.canAddOrder(trueMenu, trueCount)).isTrue();
    }

    @DisplayName("수량 더하기 - 양수만 가능, 도합 99개 초과 불가, 없는 메뉴 불가")
    @ParameterizedTest
    @CsvSource(value = {"0, 1", "100, 99"})
    public void addOrder(int exceptionCount, int successCount) {
        Menu successMenu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
        Menu exceptionMenu = new Menu(2, "후라이드", Category.CHICKEN, 16_000);
        Order order = new Order(Collections.singletonList(successMenu));
        assertThatThrownBy(() -> order.addOrder(successMenu, exceptionCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("주문");
        assertThatThrownBy(() -> order.addOrder(exceptionMenu, successCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("주문");
        assertThatThrownBy(() -> order.addOrder(exceptionMenu, exceptionCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("주문");
        order.addOrder(successMenu, successCount);
    }
}
