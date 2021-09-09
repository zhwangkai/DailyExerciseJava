package MultipleThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PracticeCallable {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> res = new FutureTask<>(myCallable);
        new Thread(res, "Thread 1").start();
        new Thread(res, "Thread 2").start();

        try{
            Integer integer = res.get();
            System.out.println(Thread.currentThread().getName() + " Print " + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Sum from 1 to 100.>>>" + Thread.currentThread().getName());
        int sum = 0;
        for (int i = 1; i<= 100; i++) {
            sum += i;
        }
        return sum;
    }
}