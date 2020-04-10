package pos.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @DisplayName("메뉴 번호와 입력한 번호가 같은 번호인지 확인")
    @Test
    public void sameNumber() {
        Menu menu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
        assertThat(menu.sameNumber(1)).isTrue();
        assertThat(menu.sameNumber(2)).isFalse();
    }

}
