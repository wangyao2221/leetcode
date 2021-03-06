package Concurrency.PrintZeroEvenOdd;

import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private int x = 0;
    private boolean change;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (x <= n) {
            if (change) {
                wait();
            } else if (!change) {
                printNumber.accept(0);
                x++;
                change = true;
                notifyAll();
            }
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (x <= n) {
            if (!(x % 2 == 0 && change)){
                wait();
            } else if (x % 2 == 0 && change) {
                printNumber.accept(x);
                change = false;
                notifyAll();
            }
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (x <= n) {
            if (!(x % 2 == 1 && change)){
                wait();
            } else if (x % 2 == 1 && change) {
                printNumber.accept(x);
                change = false;
                notifyAll();
                wait();
            }
        }
    }
}