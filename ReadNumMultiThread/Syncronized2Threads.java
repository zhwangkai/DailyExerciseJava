package ReadNumMultiThread;

import java.util.concurrent.TimeUnit;

public class Syncronized2Threads {
    static int num = 1;
    static int end = 100;

    public static void main(String[] args) throws InterruptedException {
        new Thread(Syncronized2Threads::count, "t1").start();
        new Thread(Syncronized2Threads::count, "t2").start();
        TimeUnit.MICROSECONDS.sleep(10);
        synchronized (Syncronized2Threads.class) {
            Syncronized2Threads.class.notify();
        }
    }

    public static void count() {
        synchronized (Syncronized2Threads.class) {
            while (true) {
                try {
                    Syncronized2Threads.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num > end) break;
                System.out.println("Print - " + num ++ + " Thread " + Thread.currentThread().getName());
                Syncronized2Threads.class.notify();
            }
        }
    }
}
