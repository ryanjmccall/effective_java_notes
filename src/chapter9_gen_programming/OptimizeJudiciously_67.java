package chapter9_gen_programming;

/**
 * more computing sins are computing in the name of efficiency than for any other single reason
 *
 * premature optimization is the root of all evil
 *
 * two rules of optimization
 * 1. don't do it
 * 2. (for experts only). don't do it yet—-- that is, not until you have a
 * perfectly clear and unoptimized solution
 */
public class OptimizeJudiciously_67 {

    // Strive to write good programs rather than fast ones
    // - don't sacrifice sound arch. principles for perf

    // Good programs embody the principle of information hiding:
    // where possible, they localize design decisions within individual components

    // Strive to avoid design decisions that limit perf

    // Consider the perf consequences of your API design decisions

    // it is a very bad idea to warp an API to achieve good performance

    // Measure performance before and after each attempted optimization
    // - the part of the program that you think is slow may not be at fault
    // - common wisdom says programs spend 90% of their time in 10% of their code

    // JMH is a microbenching framework providing unparalleled visibility into the
    // detailed perf of Java code

    // performance model - the relative cost of the various primitive operations
    // java has a weaker model b/c the "abstraction gap" b/w programmer and CPU execution is greater
    // - perf model varies from impl to impl and from release to release and processor to processor
    // - so you must measure the effects of optimization on each HW!

    /**
     * Strive to write good programs and speed will follow
     *
     * think about perf while designing APIs, wire-level protocols, and persistent data formats
     *
     * when you've finishing building the system, measure its perf. ***It it's fast enough, you're done.***
     *
     * if not, use profiler to locate the source of the problem
     * 1. examine choice of algorithm
     */
}
