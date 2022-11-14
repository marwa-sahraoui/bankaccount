package fr.arolla;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {
    private OperationType type;
    private LocalDate date;
    private int amount;

    public Operation(OperationType type, LocalDate date, int amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }


    public OperationType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return amount == operation.amount && type == operation.type && Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount);
    }
}
