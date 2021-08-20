package ReadNumMultiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionPrint {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static final Condition condition3 = lock.newCondition();

    public static void main (String[] args) {
        new Thread(() -> {
            for (int i = 1; i<= 100; i += 3) {
                lock.lock();
                try {
                    System.out.println("Current thread: " + Thread.currentThread().getName() + ", Print " + i);
                    condition1.await();
                    condition2.signal();
                    condition3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 2; i<= 100; i += 3) {
                lock.lock();
                try {
                    System.out.println("Current thread: " + Thread.currentThread().getName() + ", Print " + i);
                    condition1.signal();
                    condition2.await();
                    condition3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "Thread2").start();

        new Thread(() -> {
            for (int i = 3; i<= 100; i += 3) {
                lock.lock();
                try {
                    System.out.println("Current thread: " + Thread.currentThread().getName() + ", Print " + i);
                    condition1.signal();
                    condition2.signal();
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "Thread3").start();
    }
}
