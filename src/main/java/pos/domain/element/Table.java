package pos.domain.element;

public class Table {

    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public boolean isSameNo(int number) {
        return this.number == number;
    }

    public int getNumber() {
        return number;
    }
}
