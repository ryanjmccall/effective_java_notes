package chapter_5_generics;

import java.util.EmptyStackException;

public class FavorGenericType_29 {
}


class Stack<E> {
    private E[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public Stack() {
        // Can't create an array of a non-reifiable type
//        elements = new E[16];

        // solution 1
        // more readable and concise
        // does cause heap pollution -> runtime type of array doesn't match compile time
        elements = (E[]) new Object[16];
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference return result
        return result;
    }
}

/**
 * Bounded type parameter -
 * class DelayQueue<E extends Delayed> implements BlockingQ<E>
 *
 *  Generic types are safer and easier to use than types that require
 *  casts in client code. This will often mean making the types generic.
 *  If you have any existing types that can be generified, do so.
 */
