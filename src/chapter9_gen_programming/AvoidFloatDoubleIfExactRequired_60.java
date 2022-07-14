package chapter9_gen_programming;

public class AvoidFloatDoubleIfExactRequired_60 {

    /**
     * float / double perform binary floating-point arithmetic -
     * design to furnish accurate approximations quickly over a broad
     * range of magnitudes. they do not provide exact results and should
     * not be used where exact results are required
     * - ill-suited for monetary calculations
     */

    // Use `BigDecimal`, int, or long for monetary calcs
    // - less convenient and slower

}
