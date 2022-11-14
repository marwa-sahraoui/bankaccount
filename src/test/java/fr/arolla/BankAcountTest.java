package fr.arolla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.arolla.OperationType.DEPOSIT;
import static fr.arolla.OperationType.WITHDRAW;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


class BankAcountTest {

    @Test
    void withdraw_should_return_75_when_given_25() {
       BankAcount bankAcountTest = new BankAcount("ABC", 100);
       bankAcountTest.withdraw(25);
       assertThat(bankAcountTest.getBalance()).isEqualTo(75);
       //voir (ajout des operations) + UTILITÉ DU EQUAL ET HASHCOD
       assertThat(bankAcountTest.getOperations())
               .contains(new Operation(WITHDRAW, LocalDate.now(), 25));

    }
    @Test
    void withdraw_should_return_100_when_given_50() {
        BankAcount bankAcountTest2 = new BankAcount("ABC", 100);
        bankAcountTest2.withdraw(50);
        assertThat(bankAcountTest2.getBalance()).isEqualTo(50);
    }

    @Test
     void deposit_should_return_150_when_given_50() {
       BankAcount bankAcountTest = new BankAcount("ABC",100);
       bankAcountTest.deposit(50);
        assertEquals(150,bankAcountTest.getBalance());
        assertThat(bankAcountTest.getOperations())
                .contains(new Operation(DEPOSIT,LocalDate.now(),50));
       // assertThat(bankAcountTest.getOperations()).isNotEmpty();
    }
    @ParameterizedTest
    //pour prendre +eurs arguments en mm tps
    @CsvSource({"25, 75", "50, 50", "10, 90"})
    void test_withdraw_balance_should_b_updated(int amount, int expectedblance){
        //given
        BankAcount bankAcountTest=  new BankAcount("ABC",100);
        bankAcountTest.withdraw(amount);
        assertThat(bankAcountTest.getBalance()).isEqualTo(expectedblance);
    }

   @Test
    void should_return_Excepetion_when_Given_100_for_bankAccount_with50(){
        BankAcount bankAcountTest = new BankAcount("ABC",50);
        assertThatThrownBy(() -> bankAcountTest.withdraw(100))
                .isInstanceOf(NotEnoughMoneyException.class);
        assertThat(bankAcountTest.getBalance()).isEqualTo(50);
   }
   @Test
   void should_return_List_OfDate(){

       Operation operation1 = new Operation(DEPOSIT,LocalDate.now(),300);
       Operation operation2 = new Operation(WITHDRAW,LocalDate.now(),30);
       List<Operation> operations = new ArrayList<>();
       operations.add(operation1);
       operations.add(operation2);
       BankAcount bankAcountTest = new BankAcount("ABC",operations);
       assertThat(bankAcountTest.getOperationsDates()).hasSize(2);
    }

   @Test
    void should_be_equal() {
       BankAcount account1 = new BankAcount("ABC");
       BankAcount account2 = new BankAcount("ABC");
       assertThat(account1).isEqualTo(account2);
   }
   @Test
    void sould_be_not_Equal(){
        BankAcount account1 = new BankAcount("ABC");
        BankAcount account2 = new BankAcount("DEF");
        assertThat(account1).isNotEqualTo(account2);
   }



}
