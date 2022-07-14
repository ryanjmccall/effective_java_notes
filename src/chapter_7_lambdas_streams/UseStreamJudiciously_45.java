package chapter_7_lambdas_streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * Streams API - ease bulk operations sequentially or in parallel
 * stream - finite or infinite sequence of data elements
 * - come from collections, arrays, files, regex matchers, rng, other streams
 * - object references or primitives (int, long, double)
 *
 * stream pipeline - represents multistage computation on a stream
 * - consists of:
 * -- source stream
 * -- 0 or more intermediate operations
 * -- 1 terminal operation
 * --- e.g. store elements into a collection, returning certain element, printing all elements
 *
 * - lazy evaluation until the terminal operation is invoked
 * - data elements not required to complete terminal operation are never computed
 *
 * - streams API is fluent (allows chaining into a single expression)
 */
public class UseStreamJudiciously_45 {
}

// Prints all large anagram groups in a dictionary iteratively
class Anagrams {
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word),
                        (unused) -> new TreeSet<>()).add(word);
            } }
        for (Set<String> group : groups.values())
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
    }
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}

// Overuse of streams - don't do this!
class AnagramsOveruseStreams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(
                    groupingBy(word -> word.chars().sorted()
                            .collect(StringBuilder::new,
                                    (sb, c) -> sb.append((char) c),
                                    StringBuilder::append).toString()))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
}

class JustRightAnagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    // in the absence of explicit types, careful naming of lambda parameters is essential
    // to the readability of stream pipelines

    // using helper methods is even more important for readability in stream pipelines
    // than in iterative code

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}

// refrain from using streams to process char values

// refactor existing code to use streams and use them in new code only where it makes sense to do so


/**
 * Not good for
 * - read / modify local variable in scope
 * - return, break, continue, throw checked exception
 *
 * Good for:
 * - uniformly transform sequences
 * - filter sequences
 * - combine sequences (add, concat, compute min)
 * - accumulate sequences of elements into a collection, perhaps with grouping
 * - search for an element satisfying some criterion
 */


class Card{
    public Card(Suit s, Rank r) {

    }
}
enum Suit{}

enum Rank{}

class CorrespondingElements {
    // iterative Cartesian product computation
    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit: Suit.values())
            for (Rank rank: Rank.values())
                result.add(new Card(suit, rank));
        return result;
    }

    // stream-based Cartesian product computation
    private static List<Card> newDeck1() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }
}

// if you're note sure whether a task is better served by streams of iterations, try both and
// see which works better
