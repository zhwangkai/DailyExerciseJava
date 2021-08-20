package ReadNumMultiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2Threads {
    static int num = 1;
    static boolean odd = true;
    static int end = 100;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try{
                Thread.sleep(1000);
                while (num <= end) {
                    System.out.println("Start t1");
                    if (odd) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " Print - " + num++);
                        odd = false;
                        condition.signal();
                    } else {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
          lock.lock();
          try {
              Thread.sleep(3000);
              while (num <= end) {
                  System.out.println("Start t2");
                  if (!odd) {
                      System.out.println("Thread " + Thread.currentThread().getName() + " Print - " + num++);
                      odd = true;
                      condition.signal();
                  } else {
                      condition.await();
                  }
              }
          } catch (InterruptedException e ) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
        }, "t2").start();
    }
}
