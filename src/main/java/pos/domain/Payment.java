package pos.domain;

import java.util.Objects;

public class Payment {

    private final int payment;

    public Payment(int sumPrice, int countChicken, PaymentType paymentType) {
        Objects.requireNonNull(paymentType);
        validatePrices(sumPrice, countChicken);
        this.payment = paymentType.getDiscountPrice(sumPrice - ((countChicken / 10) * 10000));
    }

    private void validatePrices(int sumPrice, int countChicken) {
        if (sumPrice <= 0 || sumPrice <= (countChicken / 10) * 10000) {
            throw new IllegalArgumentException("금액 조정이 필요합니다.");
        }
    }

    public int getPayment() {
        return payment;
    }
}
