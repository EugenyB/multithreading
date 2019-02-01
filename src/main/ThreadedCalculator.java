package main;

public class ThreadedCalculator implements Runnable {

    private IntegralCalculator calculator;
    private Main main;

    public ThreadedCalculator(IntegralCalculator calculator, Main main) {
        this.calculator = calculator;
        this.main = main;
    }

    @Override
    public void run() {
        double result = calculator.calculate();
        main.sendResult(result);
    }
}


// Thread - класс
// Runnable - интерфейс