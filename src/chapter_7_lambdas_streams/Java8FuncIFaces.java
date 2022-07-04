package chapter_7_lambdas_streams;


import java.util.stream.Stream;

@FunctionalInterface
interface ShortToByteFunction {
    byte applyAsByte(short s);
}


public class Java8FuncIFaces {
    public static void main(String[] args) {
        short[] array = {(short) 1, (short) 2, (short) 3};
        byte[] tformed = transformArray(array, s -> (byte) (s*2));
        System.out.println(tformed[0] == (byte)2);
    }

    public static byte[] transformArray(short[] array, ShortToByteFunction function) {
        byte[] transformedArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            // abstract invocation
            transformedArray[i] = function.applyAsByte(array[i]);
        }
        return transformedArray;
    }

    void supplierExample() {
        int[] fibs = {0, 1};
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int res = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return res;
        });
    }
}


