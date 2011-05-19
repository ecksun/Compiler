package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Loads an integer from an array.
 */
public class Iaload extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // arrayref, index => value
        return -1;
    }

    @Override
    public String toString() {
        return "iaload" + comment();
    }

}
