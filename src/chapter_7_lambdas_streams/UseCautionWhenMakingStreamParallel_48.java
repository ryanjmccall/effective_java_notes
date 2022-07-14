package chapter_7_lambdas_streams;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class UseCautionWhenMakingStreamParallel_48 {
    /**
     * Parallelizing a pipeline is unlikely to increase its performance if the source
     * is from Stream.iterate, or the intermediate operation `limit` is used.
     *
     * Do not parallelize stream pipelines indiscriminately
     *
     * Performance gains from parallelism are best on streams over
     * ArrayList, HashMap, HashSet, and ConcurrentHashMap instances;
     * arrays; int ranges; long ranges
     *
     * - common thread: all have good-to-excellent *locality of reference* (sequential
     * elements stored together in memory)  when processed sequentially'
     *
     * - write your own Stream, Iterable, Collection implementation and override
     * `spliterator` method and test extensively
     *
     * Not only can parallelizing a stream lead to poor performance, including
     * liveness failures; it can lead to incorrect results and unpredictable behavior
     *
     * Under the right circumstances, it is possible to achieve near-linear speedup in the
     * number of processor cores simply by adding a `parallel` call to a stream pipeline
     */

    // prime-counting stream pipeline - benefits from parallelization
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    static long pi_parallel(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        System.out.println(pi_parallel(1000000));
    }
}
