package chapter9_gen_programming;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReferObjectsByInterfaces_64 {

    // if appropriate interface types exist then
    // parameters, return values, variables, and fields should all be
    // declared using interface types

    public static void main(String[] args) {
        // Good
        Set<Integer> sonSet = new LinkedHashSet<>();

        // Bad
        LinkedHashSet<Integer> sonSet2 = new LinkedHashSet<>();
    }

    // if no appropriate interface exists, use class instead

    // use base classes if appropriate

    // PriorityQueue has extra methods (rare)

    // No interface -> use the least specific class in the class
    // hierarchy that provides the required func.
}
