package pos.domain;

import java.util.Arrays;
import java.util.function.IntFunction;

public enum PaymentType {
    CARD(1, "신용 카드", (money) -> (money)),
    CASH(2, "현금", money -> (money / 100) * 95);

    private final int number;
    private final String name;
    private final IntFunction<Integer> discount;

    PaymentType(int number, String name, IntFunction<Integer> discount) {
        this.number = number;
        this.name = name;
        this.discount = discount;
    }

    public static boolean hasPaymentType(int number) {
        return Arrays.stream(PaymentType.values())
            .anyMatch(paymentType -> paymentType.number == number);
    }

    public static PaymentType of(int number) {
        return Arrays.stream(PaymentType.values())
            .filter(paymentType -> paymentType.number == number)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 PaymentType이 없습니다."));
    }

    public String getName() {
        return name;
    }

    public int getDiscountPrice(int price) {
        return discount.apply(price);
    }
}
