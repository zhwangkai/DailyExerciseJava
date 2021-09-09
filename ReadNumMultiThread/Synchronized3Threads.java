package ReadNumMultiThread;

public class Synchronized3Threads {
    public static void main(String[] args) {
        PrintNum printNum = new PrintNum(1, 1, 100);
        new Thread(() -> printNum.print(1, 2), "t1").start();
        new Thread(() -> printNum.print(2, 3), "t2").start();
        new Thread(() -> printNum.print(3, 1), "t3").start();
    }
}

class PrintNum {
    private int current;
    private int num;
    private int end;

    public PrintNum(int current, int begin, int end) {
        this.num = begin;
        this.current = current;
        this.end = end;
    }

    public void print(int flag, int next) {
        synchronized (this) {
            while (true) {
                System.out.println("-------" + Thread.currentThread().getName());
                while (current != flag && num <= end) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (num > end) break;
                System.out.println("Thread " + Thread.currentThread().getName() + " Print " + num++);
                this.current = next;
                this.notifyAll();
            }
        }
    }
}
