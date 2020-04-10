package pos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PosMenuTest {

    @DisplayName("포스 메뉴 숫자 있는지 확인하는 테스트")
    @Test
    public void hasNumber() {
        assertThat(PosMenu.hasNumber(1)).isTrue();
        assertThat(PosMenu.hasNumber(0)).isFalse();
    }

    @DisplayName("포스메뉴 of 테스트")
    @Test
    public void of() {
        PosMenu.of(1);
        assertThatThrownBy(() -> PosMenu.of(0))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
