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
        assertThatThrownBy(() -> new Order(null)).isInstanceOf(NullPointerException.class);
    }

    @DisplayName("수량을 더할 수 있는지 체크 - 양수만 가능, 도합 99개 초과 불가")
    @ParameterizedTest
    @CsvSource(value = {"0, 1", "100, 99"})
    public void addOrder(int failCount, int trueCount) {
        Menu menu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
        Order order = new Order(Collections.singletonList(menu));
        assertThat(order.canAddOrder(menu, failCount)).isFalse();
        assertThat(order.canAddOrder(menu, trueCount)).isTrue();
    }
}
