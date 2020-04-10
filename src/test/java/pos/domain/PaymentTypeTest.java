package pos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTypeTest {

    @DisplayName("of 테스트")
    @Test
    public void of() {
        PaymentType.of(1);
        assertThatThrownBy(() -> PaymentType.of(100))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당");
    }

    @DisplayName("hasPaymentType 테스트")
    @Test
    public void hasPaymentType() {
        assertThat(PaymentType.hasPaymentType(1)).isTrue();
        assertThat(PaymentType.hasPaymentType(100)).isFalse();
    }
}
