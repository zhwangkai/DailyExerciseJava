package Withdraw;

public class PeopleA extends Thread{
    Bank bank;
    public PeopleA(Bank bank) {
        this.bank = bank;
    }
    
    @Override
    public void run() {
        while(Bank.account >= 500) {
            bank.Banker(500);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
