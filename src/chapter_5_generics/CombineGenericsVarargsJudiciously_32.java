package chapter_5_generics;

import java.util.ArrayList;
import java.util.List;

public class CombineGenericsVarargsJudiciously_32 {

    // mixing generics and varargs can violate type safety!
    static void dangerous(List<String>... stringLists) {
        Object[] objects = stringLists;
        objects[0] = List.of(42); // heap pollution
        String s = stringLists[0].get(0); //ClassCastException
    }

    /**
     * while risky Java libraries do use this idiom
     * Arrays.asList(T... a)
     * Collections.addAll(Collection<? super T> c,
     * T... elements),
     * EnumSet.of(E first, E... rest)
     */

    /**
     * It is unsafe to give another method access to a generic varargs parameter array
     */
    static <T> T[] toArray(T... args) {
        return args;
    }

    // Safe method with a generic varargs parameter
    // 1. doesn't store anything in the varargs parameter array
    // 2. doesn't make the array or a clone visible to untrusted code
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T>  list: lists)
            result.addAll(list);
        return result;
    }

    // List as a typesafe alternative to a generic varargs parameter
    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list: lists)
            result.addAll(list);
        return result;
    }

    // way to allow for var args:
    // audience = flatten(List.of(friends, romans, countrymen))

}

/**
 * Summary: varargs and generics don't interact well b/c varargs is a leaky
 * abstraction built atop arrays, and arrays have different type rules from generics
 * though generic varargs parameters are not typesafe, they are legal
 * if you choose to write a method with a generic (or parameterized) varargs parameter,
 * first ensure the method is typesafe, then annotate it with @SafeVarargs
 */
