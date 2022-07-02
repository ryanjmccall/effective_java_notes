package chapter_5_generics;

import java.util.Collection;
import java.util.List;

public class UseBoundedWildcardIncrFlex_31 {
    /**
     * parameterized types are invariant
     * - List<T1> cannot be a subtype or supertype of List<T2<
     * - List<String> is not a subtype of List<Object>
     * -- cannot add Object type to List<String> so List<Object> isn't a subtype
     */

}

class StackTest<E> {
    public void push(E e){

    }
    public boolean isEmpty() {
        return true;
    }

    public E pop() {
        return null;
    }

    // Wildcard type for a parameter that serves as an E producer
    public void pushAll(Iterable<? extends E> src) {
        // Producer extends
        for (E e : src)
            push(e);
    }

    public void popAll(Collection<? super E> dst) {
        // Consumer super
        while (!isEmpty())
            dst.add(pop());
    }

    // for maximum flexibility, use wildcard types on input parameters that represent
    // producers or consumers
    // PECS stands for producer-extends, consumer-super
    // - aka Get and Put principle

    // If a parameterized type represents a T producer use <? extends T>
    // if it represents a T consumer use <? super T>
}


/**
 * // wildcard type for parameter that serves as an T producer
 * public Chooser(Collection<? extends T> choices)
 *
 * public static <E> Set<E> union(Set<E> s1, Set<E> s2)
 * --->
 * public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2)
 *
 * Dont use bounded wildcard types as return types!
 * If class client has to think about wildcard types there is probably something wrong with its API!
 *
 */

/**
 * Generally use Comparable<? super T> instead of Comparable<T>
 *     use Comparator<? super T> in preference to Comparator<T>
 *
 *  public static <T extends Comparable<? super T>> T max(List<? extends T> list)
 *
 */

interface Foo {
    // unbounded type parameter
    public <E> void swap_unbounded(List<E> list, int i, int j);

    // unbounded wildcard
    // simpler thus better for public API
    public void swap_wildcard(List<?> list , int i, int j);

    // If a type parameter appears only once in a method declaration, replace it with a wildcard
}

class ActualSwapImpl {
    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    // private helper method for  wildcard capture
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}

/**
 * Use wildcard types in APIs, while tricky, makes the APIs far more flexible
 * Producer-extends, consumer-super
 * All Comparables and Comparators are consumers
 */
