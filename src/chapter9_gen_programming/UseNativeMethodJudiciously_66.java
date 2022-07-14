package chapter9_gen_programming;

public class UseNativeMethodJudiciously_66 {

    /**
     * Java Native Interface (JNI) allows Java programs to call native methods,
     * which are methods written in native programming languages such as C or C++.
     * Three main uses
     * - provide access to platform-specific facilities such as registries
     * - provide access to existing libraries of native code, including legacy libraries
     * - used to write perf-critical parts of applications in native lang's
     *
     * It is rarely advisable to use native methods for improved perf
     *
     * think twice before using native methods. it is rare that you need to use them
     * for improved perf. if you must use native methods to access low-level
     * resources or native libraries, use a little native code as possible and test
     * it thoroughly.
     */
}
