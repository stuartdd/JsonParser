package safe;

public class Contents {
    private Double amount;

    public Contents(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void add(Double amount) {
        this.amount = this.amount + amount;
    }

    public void sub(Double amount) {
        this.amount = this.amount - amount;
    }

    public boolean isOverdrawn() {
        return getAmount() < 0;
    }

    @Override
    public String toString() {
        return " amount=" + amount +" isOverdrawn="+isOverdrawn();
    }
}
