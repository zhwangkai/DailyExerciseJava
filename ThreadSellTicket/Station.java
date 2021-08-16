package ThreadSellTicket;

public class Station extends Thread {
    public Station(String name) {
        super(name);
    }
    static int ticket = 20;
    final static Object key = "aa";
    @Override
    public void run() {
        while (ticket > 0 ) {
            synchronized (key) {
                if (ticket > 0) {
                    System.out.println(getName() + "sell the " + ticket + "tickets");
                    ticket--;
                } else {
                    System.out.println("Sold out!");
                }
            }
        }
    }
}