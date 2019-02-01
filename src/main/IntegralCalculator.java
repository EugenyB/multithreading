package main;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalculator {
    private double a;
    private double b;
    private int n;
    private double h;

    private DoubleUnaryOperator f;

    public IntegralCalculator(double a, double b, int n, DoubleUnaryOperator f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        h = (b-a)/n;
    }

    public double calculate() {
        double s = IntStream.range(0, n).mapToDouble(i -> a + i * h).map(x -> f.applyAsDouble(x)).sum();
        return s*h;
    }
}
