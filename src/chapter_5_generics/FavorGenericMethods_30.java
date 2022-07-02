package chapter_5_generics;

import java.util.*;
import java.util.function.UnaryOperator;

public class FavorGenericMethods_30 {

    /**
     * Review
     *
     * reified collection in Java is one that enforces its element types at runtime
     * - arrays are reified
     * compiles and fails and runtime:
     * Object[] arr = new Long[1];
     * arr[0] = "not a long"
     *
     * erasure collection enforces its types at compile time
     * - and "erases" the type information at runtime
     * - lists use erasure
     * won't compile:
     * List<Object> li = new ArrayList<Long>();
     * li.add("not a long");
     */

    // Generic method
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        // limitation: input and return value must all be the same
        // can be loosened using bounded wildcard types
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

//    public static void main(String[] args) {
//        Set<String> guys = Set.of("Tom", "Dick", "Harry");
//        Set<String> stooges = Set.of("Larry", "Moe", "Curly");
//        Set<String> aflCio = union(guys, stooges);
//        System.out.println(aflCio);
//    }

    /**
     * Generic singleton factory pattern examples:
     * - Collections.reverseOrder
     * - Collections.emptySet
     */

    // Generic singleton factory pattern
    private static final UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    // Sample program to exercise generic singleton
    public static void main(String[] args) {
        String[] strings = {"jute", "hemp", "nylon"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s: strings)
            System.out.println(sameString.apply(s));

        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n: numbers)
            System.out.println(sameNumber.apply(n));
    }

    /**
     * Recursive type bound: type parameter bound by some expression involving the type parameter itself
     * - commonly used in connection with Comparable
     * - collections of comparables must be mutually comparable
     * public static <E extends Comparable<E>> E max(Collection<E> e);
     * - type bound reads "any type E that can be compared to itself"
     */
    public interface ComparableEx<T> {
        int compareTo(T o);
    }

    // Returns max value in a collections - uses recursive type bound
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");

        E result = null;
        for (E e: c)
            if (result == null || e.compareTo(result) > 0)
                return Optional.of(Objects.requireNonNull(e));

        // page 159
        return Optional.ofNullable(result);
    }

    /**
     * Summary: generic methods require clients to put explicit casts on inputs and return values
     */

}

