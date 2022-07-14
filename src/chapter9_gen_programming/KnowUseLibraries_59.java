package chapter9_gen_programming;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class KnowUseLibraries_59 {

    /**
     * example don't write your own random function, use library
     * Random.nextInt(int)
     */

    // RNG of choice is not ThreadLocalRandom

    // Numerous features are added to the libraries in every major release,
    // and it pays to keep abreast of these additions

    // print contents of a URL with transferTo (Java 9)
    public static void main(String[] args) throws IOException {
        try (InputStream in = new URL(args[0]).openStream()) {
            in.transferTo(System.out);
        }
    }

    // every programmer should be familiar with the basics of:
    // java.lang
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/package-summary.html

    // java.util
    // java.io, and their subpackages
    // other libraries can be acquired JIT

    /**
     * Guava is a set of core Java libraries from Google that includes new collection types
     * (such as multimap and multiset), immutable collections, a graph library, and utilities
     * for concurrency, I/O, hashing, caching, primitives, strings, and more!
     */

    // generally speaking, library code is likely to be better than code that you'd write yourself
    // and is likely to improve over time
}
