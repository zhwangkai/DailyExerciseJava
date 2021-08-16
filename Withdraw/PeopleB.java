package Withdraw;

public class PeopleB extends Thread{
    Bank bank;
    public PeopleB(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        while(Bank.account >= 1000) {
            bank.ATM(1000);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
