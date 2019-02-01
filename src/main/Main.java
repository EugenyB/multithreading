package main;

import static java.lang.Math.PI;

public class Main {

    private double sum;
    private int count;

    public static void main(String[] args) {
	    new Main().run();

    }

    private void run() {
        double a = 0;
        double b = PI;
        int n = 1_000_000_000;
        int threads = 10;
        sum = 0;
        count = 0;
        double delta = (b-a)/threads;
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            IntegralCalculator calculator = new IntegralCalculator(a + i * delta, a + (i + 1) * delta, n / threads, x -> Math.sin(x));
            ThreadedCalculator threadedCalculator = new ThreadedCalculator(calculator, this);
            new Thread(threadedCalculator).start();
        }
        synchronized (this) {
            try {
                while (threads > count) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println(finish-start);
    }

    private void run1() {
        IntegralCalculator calculator = new IntegralCalculator(0, PI, 100_000_000, x->Math.sin(x));
        long start = System.currentTimeMillis();
        double res = calculator.calculate();
        long finish = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(finish-start);
    }

    public synchronized void sendResult(double result) {
        sum += result;
        count++;
        notify();
    }
}
