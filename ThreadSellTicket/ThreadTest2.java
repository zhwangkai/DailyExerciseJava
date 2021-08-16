package ThreadSellTicket;

public class ThreadTest2 {
    public static void main (String[] args) {
        System.out.println(">>>>>Program start here: ");
        Station s1 = new Station("Window1");
        Station s2 = new Station("Window2");
        Station s3 = new Station("Window3");

        s1.start();
        s2.start();
        s3.start();
    }

}

