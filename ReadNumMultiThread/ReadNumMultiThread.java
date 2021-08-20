package ReadNumMultiThread;

public class ReadNumMultiThread {
    volatile int i = 1;
    public static void main(String[] args) {
        System.out.println(">>>>Let's go!");

        ReadNumMultiThread rn = new ReadNumMultiThread();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (rn.i <= 100) {
                    synchronized (this) {
                        notifyAll();
                        if(rn.i == 101) {
                            return;
                        }

                        String name = Thread.currentThread().getName();
                        int i = Integer.parseInt(name);
                        if (rn.i % 3 == i) {
                            System.out.println(rn.i + " - Thread" + name);
                            rn.i ++;
                        }
//                        if (rn.i % 3 == 1) {
//                            System.out.println("======");
//                        }
                        try {
                            if (rn.i == 101) {
                                notifyAll();
                                return;
                            } else {
                                wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.setName("1");
        t2.setName("2");
        t3.setName("0");
        t1.start();
        t2.start();
        t3.start();
    }
}
