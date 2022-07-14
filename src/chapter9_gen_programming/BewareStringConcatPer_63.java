package chapter9_gen_programming;

public class BewareStringConcatPer_63 {

    // String concat operator repeatedly on N strings requires O(n^2)

    // Strings are immutable, when two strings are concatenated,
    // the contents of both are copied

    // Use StringBuilder in place of String
    public static void main(String[] args) {
        StringBuilder b = new StringBuilder(500);
        for (int i = 0; i < 100; i++)
            b.append(i);
        System.out.println(b.toString());
    }

    // only use concat for a couple of strings
}
