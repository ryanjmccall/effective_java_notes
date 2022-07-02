package chapter_5_generics;

public class DontUseRawTypes_26 {
    /**
     *  with generics you tell the compiler what types of objects are permitted in each collection
     *  the compiler inserts casts for you automatically and tells you at compile time if you try
     *  to insert an object of the wrong type
     */

    /**
     * type parameter - e.g. E or T
     * generic class - class whose declaration has 1 or more type parameters
     * -- e.g. List<E>
     *
     * generic types - generic classes and interfaces
     * parameterized types - class of interface name followed by an angle-bracketed list of
     *   actual type parameters corresponding to the generic type's formal type parameters
     * -- e.g. List<String>
     *
     * raw type - name of the generic type used without any accompanying type parameters
     * -- e.g. raw type of List<E> is List  (type declaration with all generic type info erased)
     *
     * Don't Use Raw Types
     *
     * List vs. List<Object>
     * - former opts out of generic type system, while latter tells compiler
     * - List foo = List<String> OK
     * - List<Object> = List<String> ERROR
     *
     * you lose type safety if you use a raw type, but not if you use a parameterized type
     *
     * Unbounded wildcard types - when you want a generic type but you don't care what the
     * actual type parameter is, signified with a '?'
     * -- e.g., Set<?> (read "set of some type")
     *
     * Exceptions
     * - must use raw types in class literals
     * - when checking instanceof
     *
     * if (o instanceof Set) {    // raw type
     *     Set<?> s = (Set<?>) o; // wildcard type
     * }
     *
     * Raw type                 List
     * Generic type -           List<E>
     * Parameterized type -     List<String>
     * Formal type parameter -  E
     * Actual type parameter -  String
     * Unbounded wildcard type - List<?>
     * Bounded type parameter  - <E extends Number>
     * recursive type bound     <T extends Comparable<T>>
     * Bounded wildcard type    List<? extends Number>
     * Generic method           static <E> List<E> asList(E[] a)
     * Type token               String.class
     */
}
