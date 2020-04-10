package pos.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pos.domain.element.MenuRepository;
import pos.domain.element.TableRepository;

public class PosStatusTest {

    @DisplayName("생성자 Null 안됨")
    @Test
    public void createPosStatus() {
        new PosStatus(TableRepository.tables(), new Order(MenuRepository.menus()));
        assertThatThrownBy(() -> new PosStatus(null, new Order(MenuRepository.menus())))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new PosStatus(TableRepository.tables(), null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new PosStatus(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

}
