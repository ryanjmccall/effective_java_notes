package chapter_5_generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EliminateUncheckedWarnings_27 {

    /**
     * eliminate every unchecked warning that you can!!
     *
     * if you can't eliminate, and you can prove the code is
     * typesafe, then and only then, suppress the warning
     * - @SuppressWarnings("unchecked")
     * - include a comment saying why it is safe to suppress
     */

    /**
     * arrays are covariant - if Sub is a subtype of Super,
     * then Sub[] is a subtype of the array type Super[] (they are heritable)
     * generics are invariant - for any two distinct types T1 and T2, List<T1> is neither
     * a subtype or supertype of List<T2>
     *
     * arrays are reified - arrays know and enforce their element type at RUNTIME
     * generics are implemented by erasure - means they enforce their type constraints only at
     * compile time and DISCARD / ERASE their element type information at runtime. erasure allowed for
     * backward compatibility with legacy code that didn't use generics
     * > arrays and generics do not mix well
     * illegal to create a generic array b/c it isn't typesafe
     *
     * Non-reifiable types - E, List<E List<String runtime repr contains less info than compile time repr
     * - b/c of erasure the only parameterized types that are reifiable are unbounded wildcard types
     * such as List<?> and Map<?,?>
     *
     * Can use List<E> In Lieu of E[] for better safety and interoperability with less performance
     *
     * Arrays are covariant and reified
     * - provide runtime type safety but not compile-time type safety
     * Generics are invariant and erased
     * - provide compile-time safety but not runtime
     */

}

// List-based Chooser - typesafe
class Chooser<T> {
    private final List<T> choiceList;

    public Chooser(Collection<T> choices) {
        choiceList  = new ArrayList<>(choices);
    }

    public T choose() {
        return choiceList.get(0);
    }
}
