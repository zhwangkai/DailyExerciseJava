package ReadNumMultiThread;

import java.util.concurrent.locks.LockSupport;

public class Park3Threads {
    static int end = 100;
    static int num = 1;
    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void print(Thread next) {
        while (true) {
            System.out.println(">>" + Thread.currentThread().getName());
            LockSupport.park();
            if(num >end) break;
            System.out.println("Thread " + Thread.currentThread().getName() + " print " + num++);
            LockSupport.unpark(next);
        }
        LockSupport.unpark(next);
    }

    public static void main(String[] args) {
        t1 = new Thread(() -> Park3Threads.print(t2), "t1");
        t2 = new Thread(() -> Park3Threads.print(t3), "t2");
        t3 = new Thread(() -> Park3Threads.print(t1), "t3");
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}
