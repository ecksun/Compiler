package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Push short onto stack as an integer value.
 */
public class Sipush extends Instruction {

    /**
     * The short value to be pushed onto stack as an integer.
     */
    private int value;

    @Override
    public int getOperandStackSizeChange() {
        // => value
        return 1;
    }

    @Override
    public String toString() {
        return "sipush " + value + comment();
    }

}
