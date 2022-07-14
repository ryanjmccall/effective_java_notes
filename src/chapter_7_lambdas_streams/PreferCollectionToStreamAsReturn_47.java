package chapter_7_lambdas_streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PreferCollectionToStreamAsReturn_47 {

    // Adapter from Stream<E> to Iterable<E>
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    // Adapter from Iterable<E> to Stream<E>
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {
        for (ProcessHandle p: iterableOf(ProcessHandle.allProcesses())) {
            // Process the process
            System.out.println(p.info());
        }
    }

    // Collection is generally the best return type for a public, sequence-returning method
    // - fits in memory -> ArrayList, HashSet
    // do not store a large sequence in memory only to return it as a collection
    // Collection has an int-returning size method, which limits the
    // length of the returned sequence.
    // Can write a Collection implementation atop AbstractCollection
    // - only have to impl contains and size
}


class Sublists {
    public static <E> Stream<List<E>> of (List<E> list) {
        return Stream.concat(Stream.of(Collections.emptyList()),
                prefixes(list).flatMap(Sublists::suffixes)
        );
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }

    // Returns a stream of all the sublists of its input list
    public static <E> Stream<List<E>> of_list(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start ->
                        IntStream.rangeClosed(start + 1, list.size())
                                .mapToObj(end -> list.subList(start, end)))
                .flatMap(x -> x);
    }
}

/**
 * Summary
 * When writing a method that returns a sequence of elements, remember that some of your users
 * may want to process them as a stream while others iterate over them.
 * - if you can return a Collection do so
 * - if possible return standard collection such as ArrayList
 * - otherwise, consider a custom collection
 * - if collection not feasible, return a stream of iterable, whichever seems more natural

 */

