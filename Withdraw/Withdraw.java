package Withdraw;

public class Withdraw {
    public static void main(String[] args) {
        System.out.println(">>>>>>Program start here: ");
        Bank bank = new Bank();
        PeopleA peopleA = new PeopleA(bank);
        PeopleB peopleB = new PeopleB(bank);

        peopleA.start();
        peopleB.start();
    }
}
