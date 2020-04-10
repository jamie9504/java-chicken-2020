package pos.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @DisplayName("생성자 테스트 - Null 비허용")
    @Test
    public void createPayment() {
        int sum = 100000;
        int countChicken = 14;
        PaymentType paymentType = PaymentType.CASH;
        new Price(sum, countChicken, paymentType);
        assertThatThrownBy(() -> new Price(sum, countChicken, null))
            .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("생성자 테스트 - Sum은 양수여야 하고, countChicken / 10 * 10000보다 커야 함")
    @Test
    public void createPaymentValidate() {
        assertThatThrownBy(() -> new Price(-100000, 14, PaymentType.CASH))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Price(0, 14, PaymentType.CASH))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Price(10000, 19, PaymentType.CASH))
            .isInstanceOf(IllegalArgumentException.class);
        new Price(10000, 9, PaymentType.CASH);
    }
}
