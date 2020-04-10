package pos.domain;

import java.util.Arrays;

public enum PosMenu {
    ORDER(1, "주문등록", true),
    PAYMENT(2, "결제하기", true),
    END(3, "프로그램 종료", false);

    private final int number;
    private final String name;
    private final boolean repeat;

    PosMenu(int number, String name, boolean repeat) {
        this.number = number;
        this.name = name;
        this.repeat = repeat;
    }

    public static boolean hasNumber(int number) {
        return Arrays.stream(PosMenu.values())
            .anyMatch(posMenu -> posMenu.number == number);
    }

    public static PosMenu of(int number) {
        return Arrays.stream(PosMenu.values())
            .filter(posMenu -> posMenu.number == number)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("원하는 기능을 숫자로 잘 선택해주세요."));
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
