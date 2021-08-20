package ReadNumMultiThread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchPrint {
    public static void main (String[] args) {
        final Object o = new Object();
        CountDownLatch cdl1 = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        CountDownLatch cdl3 = new CountDownLatch(1);

        new Thread(() -> {
            synchronized (o) {
                for (int i = 1; i <= 100; i += 2) {
                    System.out.println("Print " + i + " - Current Thread: " + Thread.currentThread().getName());
                    try {
                        cdl2.countDown();
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A").start();

        new Thread(() -> {
            try {
                cdl2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (int i = 2; i <= 100; i += 2) {
                    System.out.println("Print " + i + " - Current Thread: " + Thread.currentThread().getName());
                    try {
                        cdl3.countDown();
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();

//        new Thread(() -> {
//            try {
//                cdl3.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (o) {
//                for (int i = 3; i <= 100; i += 3) {
//                    System.out.println("Print " + i + " - Current Thread: " + Thread.currentThread().getName());
//                    try {
//                        cdl1.countDown();
//                        o.notify();
//                        o.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                o.notify();
//            }
//        }, "C").start();
    }
}
