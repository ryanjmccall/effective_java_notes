package chapter_7_lambdas_streams;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferMethodRefsToLambdas_43 {

    public static void main(String[] a) {
        Map<String, Integer> freq = new HashMap<>();

        Collection<String> data = List.of("a", "b", "c", "a");
        for (String s : data)
            // using lambda
            freq.merge(s, 1, (count, incr) -> count + incr);

        System.out.println(freq);
        freq.clear();

        for (String s: data)
            // using method reference
            freq.merge(s, 1, Integer::sum);

        System.out.println(freq);

        // method references are more succinct than lambdas
    }

    /**
     * Method Reference Type
     * Example
     * Lambda equivalent
     *
     * Static
     * Integer.parseInt
     * str -> Integer.parseInt(str)
     *
     * Bound (receiving object specified in the method ref)
     * Instant.now()::isAfter
     * Instant then = Instant.now();
     * t -> then.isAfter(t)
     *
     * Unbound
     * String::toLowerCase
     * str -> str.toLowerCase()
     *
     * Class Constructor
     * TreeMap<K,V>::new
     * () -> new TreeMap<K,V>
     *
     * Array Constructor
     * int[]::new
     * len -> new int[len]
     */

    // Where method references are shorter and clearer, use them; where they aren't,
    // stick with lambdas
}
