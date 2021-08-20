package ReadNumMultiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionPrint2 {
    private volatile int count = 1;
    private final int maxNum;
    private int state;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static final Condition condition3 = lock.newCondition();
    public LockConditionPrint2(int maxNum) {
        this. maxNum = maxNum;
    }

    public static void main (String[] args) {
        LockConditionPrint2 lockConditionPrint2 = new LockConditionPrint2(100);
        new Thread(() -> {
            lockConditionPrint2.print(0, condition1, condition2);
        }, "A").start();
        new Thread(() -> {
            lockConditionPrint2.print(1, condition2, condition3);
        }, "B").start();
        new Thread(() -> {
            lockConditionPrint2.print(2, condition3, condition1);
        }, "C").start();
    }

    private void print(int targetState, Condition current, Condition next) {
        for (int j = 1; j <= maxNum / 3 + 1;) {
            lock.lock();
            try {
                while (state % 3 != targetState) {
                    current.await();
                }
                System.out.println("Current Thread: " + Thread.currentThread().getName() + ", print " + count++);
                state++;
                j++;
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
