package chapter9_gen_programming;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class PreferForEachLoops_58 {

    void foo() {
        // preferred idiom for iterating over collections and arrays
        List<Element> elements = new ArrayList<>();
        for (Element e : elements) {
            System.out.println(e.getTagName());
        }

        // preferred idiom for nested iteration on collections/arrays
        //        for (Suit suit : suits)
        //            for (Rank rank: ranks)
        //                desk.add(new Card(suit, rank));
    }

    /**
     * CANT use for-each
     * - destructive filtering requires iterator or Collection.removeIf
     * - transforming - replace values
     * - parallel iteration - traverse multiple collections in parallel requires explicit control
     */

    /**
     * for-each loop works on any `Iterable`
     *
     * public interface Iterable<E> {
     *     Iterator<E> iterator();
     * }
     *
     * if you are writing a type that represents a group of elements, you should
     * strongly consider having it implement `Iterable`, even if it's not a collection
     */
}
