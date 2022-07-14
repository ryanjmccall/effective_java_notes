package chapter_7_lambdas_streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import static java.util.Comparator.comparingInt;

public class PreferLambdasToAnonClasses_42 {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();

        // Obsolete Anonymous class instance as a function object
        Collections.sort(words,
                new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.compare(s1.length(), s2.length());
                    }
                }
        );

        // Lambda expression as function object
        Collections.sort(words,
                (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        //compiler deduces parameter and return types from context (type inference)
        // Omit types of all lambda parameters unless their presence makes your
        // program clearer

        // Comparator construction method
        Collections.sort(words, comparingInt(String::length));

        // alternative
        words.sort(comparingInt(String::length));


    }
}

// Enum with function object fields and constant-specific behavior
enum Operation {
    PLUS ("+", Double::sum),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE ("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() {return symbol;}

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}

// lambdas lack names and documentation; if a computation isn't self-explanatory,
// or exceeds a few lines, don't put it in a lambda (1-3 lines are reasonable)
// lambdas are limited to functional interfaces, cannot instantiate abstract classes,
// and cannot obtain a reference to itself (use anon class)

// Rarely, if ever, serialize a lambda b/c you can't reliably do it across implementations

// Don't use anon classes for function objects unless you have to create instances of types
// that aren't functional interfaces
