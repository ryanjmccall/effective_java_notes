package chapter_2;

import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

class CreatingObjects {
    public static void main(String[] args) {
        System.out.println("hi");
    }

    /**
    Item 1: consider static factory methods instead of constructors
    1. they have descriptive names
    2. allows object caching, don't have to create a new instance each time (similar to flyweight)
    - instance controlled classes
    - interface-based frameworks: interfaces provide natural return types for static factory methods
    3. can return an object of any subtype of their return type
    4. returned object can vary as a function of input parameters
    5. class of the returned object need not exist when the class containing the method is written
    Service provider framework: service interface, provider registration API, service access API
    - e.g. DI

     Limitations
     - classes without public or protected constructors cannot be subclassed
     - static factory methods hard to find

     Common examples:
     from, of, valueOf, instance, getInstance, create/newInstance, getType (getFileStore), newType (newBufferedReader),
     type (Collections.list)
     */
    public void staticFactoryMethods() {
//        Date d = Date.from("123");
//        Set<Integer> faceCards = EnumSet.of(1, 2, 3);
        BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
        StackWalker luke = StackWalker.getInstance();
//        Object newArray = Array.newInstance(classObj, arrayLen);
//        FileStore fs = Files.getFileStore("");
        String path = "";
//        BufferedReader br = Files.newBufferedReader(path);
    }

    /**
     *
     */
    public void builderWithManyCtorParams() {

    }

}


class NutritionFacts {
    private final int servingSize;
//    private final int servings;
    private final int calories;
//    private final int fat;
    private final int sodium = 0;
    private final int carbohydrate = 0;

    public static class Builder {
        // required
        private final int servingSize;

        // optional
        private int calories = 0;

        public Builder(int servingSize) {
            this.servingSize = servingSize;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        //
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
//        servings = builder.servings;
        calories = builder.calories;
//        fat = builder.fat;

    }

    /**
     * Item 3: enforce the singleton property with a private constructor or an enum type
     * single-element enum type is often the best way to implement a singleton
     */

    /**
     * Item 4:
     * enforce noninstantiability with a private constructor
     */

    /**
     * Item 5:
     * Prefer dependency injection to hard-wiring resources
     * - variant: pass resource factory to the ctor
     */

    /**
     * Item 6: Avoid creating unnecessary objects.
     * Do: String s = "bikini";
     * Don't: String s = new String("bikini");
     * Prefer primitives (long) to boxed primitives (Long sum = 0L), and watch out for autoboxing
     */

    /**
     * Item 7: Eliminate obsolete object references
     * - Nulling out object references should be the exception rather than the norm.
     * - Whenever a class manages its own memory, the programmer should be alert for memory leaks.
     * - Another common source of memory leaks is caches.
     * - A third common source of memory leaks is listeners and other callbacks
     */

    /**
     * Item 8: Avoid finalizers and cleaners
     * Finalizers are unpredictable, often dangerous, and generally unnecessary.
     * - cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary
     * - never do anything time-critical in a finalizer or cleaner
     * - never depend on a finalizer or cleaner to update persistent state
     * - there is a severe performance penalty for using finalizers and cleaners
     * - finalizers have a serious security problem: they open your class up to finalizer attacks
     * - throwing an exception from a constructor should be sufficient to prevent an object from coming into existence;
     * in the presence of finalizers, it is not
     * - to protect nonfinal classes from finalizer attacks, write a final finalize method that does nothing
     */

    /**
     * Item 9: Prefer try-with-resources to try-finally
     * - resource must implement AutoCloseable
     */


    /**
     * Pages
     * 20-35
     * 35-40
     * 40-55
     */

}
