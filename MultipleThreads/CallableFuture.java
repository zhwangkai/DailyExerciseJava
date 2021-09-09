package MultipleThreads;

public class CallableFuture {
    public static void main(String[] args) {
        System.out.println(">>>Start");
        new Thread(() -> {
            synchronized (CallableFuture.class){
                System.out.println("This is " + Thread.currentThread().getName());
                CallableFuture.class.notify();
            }
        }, "Thread one").start();
        new Thread(() -> {
            System.out.println("This is " + Thread.currentThread().getName());
        }, "Thread Two").start();
    }

}
