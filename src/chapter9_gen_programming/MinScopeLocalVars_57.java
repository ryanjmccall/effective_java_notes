package chapter9_gen_programming;

public class MinScopeLocalVars_57 {
    // most powerful technique for minimizing local var scope is to declare it
    // where it is first used

    // nearly every local variable declaration should contain an initializer
    // - exception: try-catch statements

    // prefer `for` loops to `while` loops, assuming loop variable not needed after
    // loop termination

    /**
     * // over a collection or array
     * for (Element e: c) {
     *     //
     * }
     *
     * // when you require the iterator
     * for (Iterator<Element> i = c.iterator(); i.hasNext(); ) {
     *     Element e = i.next();
     *     // do something with `e` and `i`
     * }
     *
     * // minimizes the scope of local variables
     * for (int i = 0, n = expensiveComputation(); i < n; i++) {
     *     // Do something with i;
     * }
     */

    // Keep methods small and focused
}
