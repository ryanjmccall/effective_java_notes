package chapter_7_lambdas_streams;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * each xform should be close to a pure function
 * pure function - one whose result only depends on its input; no interaction with mutable state
 * - any functions passed into stream operations, intermediate or terminal, should be free of
 * side-effects
 */
public class SideEffectFreeFuncsInStreams_46 {

    // BAD: uses streams API but not the paradigm
    public void badExample() {
        String file = "a/b/c";
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(
              word -> {
                  // mutates external state
                  freq.merge(word.toLowerCase(), 1L, Long::sum);
              }
            );
        }
    }

    // GOOD: proper use of streams of compute freq table
    public void goodExample() {
        Map<String, Long> freq;
        String file = "a/b/c";
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }
    }

    // the forEach operation should be used only to REPORT the result of a stream computation,
    // not to perform the computation

    /**
     * Collector - an opaque object that encapsulates a reduction strategy.
     * - combining elements of a stream into a single object (typically a collection)
     * Get a `Collection`
     * - toList(), toSet(), toCollection(collectionFactory)
     */

    public List<String> topTenFreq(Map<String, Long> freq) {
        return freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(10)
                .collect(toList());
    }

    public void makeMapFromStringToEnum() {
        // See Item 34
//        Map<String, Operation> stringToEnum =
//                Stream.of(values()).collect(toMap(Object::toString, e -> e));
    }

    // collector to generator a map from key to chosen element for a key
    static class Artist {}
    static class Album {
        Artist artist() {return null;}
        int sales() {return 1;}
    }

    public void foo() {
//        List<Album> albums = new ArrayList<>();
//        Map<Artist, Album> topHits = albums.stream().collect(
//            toMap(Album::artist, a->a, maxBy(comparing(Album::sales)))
//        );
    }

    /**
     * Collector to impose last-write-wins policy
     * toMap(keyMapper, valueMapper, (v1, v2) -> v2)
     *
     * Classifier function - takes an element and returns the category  into which it falls
     * - groupingBy() method returns collectors to produce maps that group elements into
     * categories based on classifier function
     * - example:
     * words.collect(groupingBy(word -> alphabetize(word)))
     *
     * if you want groupingBy to return a collector that produces a map with values other than lists
     * you can specify a downstream collector in addition to a classifier.
     * a downstream collector produces a value from a stream containing all the elements in a category
     * the simplest use of this parameter is to pass `toSet()` which results in a map whose values are
     * sets of elements rather than lists
     * alternatively, you can pass `toCollection(collectionFactory)` allowing you to create
     * collections into which each category of elements is placed. this gives you the flexibility to
     * choose any collection type you want
     * Maps<String, Long> freq = words.collect(groupingBy(String::toLowerCase, counting());
     *
     * Collectors.joining - operates on CharSequence types
     */

    /**
     * Summary, the essence of programming stream pipelines is side-effect-free function objects
     * This applies to all of the many function objects passed to stream and related objects
     * the terminal `forEach` should only be used to report the result of a computation performed
     * by a stream, not to perform the computation. In order to use streams properly, you have to
     * know about collectors. The most important collector factories are
     * toList, toSet, toMap, groupingBy, and joining
     */
}
