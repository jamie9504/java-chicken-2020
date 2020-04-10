package pos.domain.element;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TableTest {

    @DisplayName("테이블 번호와 제공한 번호가 같은지 확인")
    @Test
    public void isSameNo() {
        Table table = new Table(1);
        assertThat(table.isSameNo(0)).isFalse();
        assertThat(table.isSameNo(1)).isTrue();
        assertThat(table.isSameNo(2)).isFalse();
    }
}
