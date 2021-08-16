package Withdraw;

public class Bank {
    static int account = 10000;
    public void Banker(int money) {
        Bank.account -= money;
        System.out.println("Withdraw through Banker " + money + ", Balance = " + Bank.account);
    }

    public void ATM(int money) {
        Bank.account -= money;
        System.out.println("Withdraw through ATM " + money + ", Balance = " + Bank.account);
    }
}
