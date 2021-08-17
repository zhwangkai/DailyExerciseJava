package CountdownlatchPractice;

import java.util.concurrent.CountDownLatch;

public class CountdownlatchPractice {
    public static void main(String[] args) {
        System.out.println(">>>>>>Start Here: ");
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);
        CountDownLatch c3 = new CountDownLatch(1);
        final int[] i = {1};
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i[0]++);
                System.out.println(Thread.currentThread().getName());
                c2.countDown();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i[0]++);
                System.out.println(Thread.currentThread().getName());
                c3.countDown();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i[0]++);
                System.out.println(Thread.currentThread().getName());
                c1.countDown();
            }
        });

        thread3.start();
        thread2.start();
        thread1.start();

        c1.countDown();
    }

}
