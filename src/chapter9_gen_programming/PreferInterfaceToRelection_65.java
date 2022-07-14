package chapter9_gen_programming;

public class PreferInterfaceToRelection_65 {

    /**
     * reflection drawbacks
     * - lose benefits of compile-time type checking
     * - code required is clumsy and verbose
     * - perf suffers
     *
     * when in doubt, go without (reflection)
     *
     * you can obtain many of the reflection benefits while incurring
     * few of its costs by using it only in a very limited form
     * - create instances reflectively and access them normally via
     * interface or superclasss
     *
     * Reflection is required for some tasks but it has many disadvantages
     * - if you are writing a program that has to work with classes
     * unknown at compile time, you should, if at all possible,
     * use reflection only to instantiate objects, and access the
     * objects using some interface or superclass that is known at compile time
     */
}
