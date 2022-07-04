package chapter_7_lambdas_streams;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FavorStandardFuncInterfaces_46 {
}

// Unnecessary functional interface; use a standard one instead
@FunctionalInterface interface EldestEntryRemovalFunction<K, V> {
    boolean remove(Map<K,V> map, Map.Entry<K,V> eldest);
}

/**
 * if one of the standard functional interfaces does the job,
 * you should generally use it in preference to a purpose-built
 * functional interface
 *
 * preserves type
 * UnaryOperator<T>
 * T apply(T t)
 * String::toLowerCase
 *
 * preserves type 2-params
 * BinaryOperator<T>
 * T apply(T t1, T t2)
 * BigInteger::add
 *
 * Predicate<T>
 * boolean test(T t)
 * Collection::isEmpty
 *
 * Function<T, R>
 * R apply(T t)
 * Arrays::asList
 *
 * Supplier<T>
 * T get()
 * Instant:now
 *
 * Consumer<T>
 * void accept(T t)
 * System.out::println
 */

// DONT use basic functional interfaces with boxed primitives
// instead of primitive functional interfaces

/**
 * Functional interface - an interface with a single abstract method
 * - can use lambda expressions to instantiate them and avoid
 * using bulky anonymous class implementation
 */

class FuncIFace {
    public static void main(String[] a) {
        Map<String, Integer> nameMap = new HashMap<>();
        Integer v = nameMap.computeIfAbsent("John", s -> s.length());

        //Method reference replacement, method invoked on first argument
        Integer v1 = nameMap.computeIfAbsent("John", String::length);

        //
        Function<Integer, String> inToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        Function<Integer, String> combined = quote.compose(inToString);
    }
}

// Always annotate your functional interfaces with annotation
/**
 * Design APIs with lambdas in mind.
 * Accept functional interface types on input and return on output
 * Generally prefer iface types in java.util.function.Function
 */
