package ReadNumMultiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLook3Threads {
    static int num = 1;
    static int end = 100;

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();
    static Condition condition3 = lock.newCondition();

    public static void print(Condition current, Condition next) {
        lock.lock();
        try {
            while (true) {
                System.out.println("===" + Thread.currentThread().getName());
                current.await();
                if (num > end) break;
                System.out.println("Thread " + Thread.currentThread().getName() + " print " + num++);
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            next.signal();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> print(condition1, condition2), "t1").start();
        new Thread(() -> print(condition2, condition3), "t2").start();
        new Thread(() -> print(condition3, condition1), "t3").start();
        if (lock.tryLock()) {
            try {
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
