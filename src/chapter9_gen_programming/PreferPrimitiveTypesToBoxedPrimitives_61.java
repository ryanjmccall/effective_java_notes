package chapter9_gen_programming;

import java.util.Comparator;

public class PreferPrimitiveTypesToBoxedPrimitives_61 {

    // primitive types: int, double, boolean
    // reference types: String and List
    // boxed primitive - reference type corresponding to each primitive type (Integer, Double, Boolean)

    // two boxed primitives instances can have the same value and different identities
    // boxed primitive type can be null
    // primitives are more time- and space- efficient

    // Applying the == operator to boxed primitives is almost always wrong

    // to avoid the error
    void foo() {
        Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
            int i = iBoxed, j = jBoxed; // Auto-unboxing
            return i < j ? -1 : (i == j ? 0 : 1);
        };
    }

    // when you mix primitives and boxed primitives in an operation, the boxed primitive is
    //   auto-unboxed

    // slow due to object creation...
    public static void main(String[] args) {
        Long sum = 0L;   // should be long
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    // Autoboxing reduce the verbosity, but not the danger, of using boxed primitives
    // - == still does an identity comparison instead of value comp

    //  when your program does unboxing, it can throw a NullPointerException
}



















