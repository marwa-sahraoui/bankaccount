package fr.arolla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BankAcount {

    private final String iban;

    //ajout de list d'operations
    private List<Operation> operations;

    public BankAcount(String iban, int balance) {
        this.iban = iban;
        this.operations = new ArrayList<>();
        deposit(balance);
    }

    public BankAcount(String iban) {
        this(iban, 0);
    }

    public BankAcount(String iban, List<Operation> operations) {
        this.iban = iban;
        this.operations = operations;
    }

    public void withdraw(int amount){
         if(amount > getBalance()) {
             throw new NotEnoughMoneyException();
         }
         operations.add(new Operation(OperationType.WITHDRAW,LocalDate.now(),amount));
    }

    void deposit(int amount){
         operations.add(new Operation(OperationType.DEPOSIT,LocalDate.now(),amount));
    }

    public int getBalance() {
        int deposit = operations.stream()
                .filter(operation -> operation.getType().equals(OperationType.DEPOSIT))
                .mapToInt(operation -> operation.getAmount())
                .sum();

        int withdraw = operations.stream()
                .filter(operation -> operation.getType().equals(OperationType.WITHDRAW))
                .mapToInt(operation -> -operation.getAmount())
                .sum();

        return deposit + withdraw;
    }


   public List<Operation> getOperations(){
        return operations;
    }


    List<LocalDate> getOperationsDates(){
        return  operations.stream()
                .map(Operation::getDate).collect(Collectors.toList());
     }

     List<Operation> operationsWithDrawBetween2Date(LocalDate startDate, LocalDate endDate){
        return operations.stream()
                .filter(operation -> operation.getType().equals(OperationType.WITHDRAW))
                .filter(operation -> operation.getDate().isAfter(startDate))
                .filter(operation -> operation.getDate().isBefore(endDate))
                .toList();
     }
     ////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAcount)) return false;
        BankAcount that = (BankAcount) o;
        return Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
