package chapter9_gen_programming;

public class AvoidStringsWhenOtherTypesWork_62 {
    // Strings are poor substitutes for other value types
    // Strings are poor substitutes for Enum types
    // ... for aggregate types

    void foo() {
        // inappropriate use of String as aggregate type
//        String compoundKey = className + "#" + i.next();
    }

    // don't use strings to roll your own threadlocal


}


// close approx to java.lang.ThreadLocal
final class MyThreadLocal<T> {
    public MyThreadLocal() {}
    public void set(T value) {}
    public T get() {return null;}
}

// Avoid representing objects as strings when better data types exist or can be written
// common misuse: primitives, enums, aggregates
