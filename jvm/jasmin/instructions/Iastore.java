package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Store an integer into an array.
 */
public class Iastore extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // arrayref, index, value =>
        return -3;
    }

    @Override
    public String toString() {
        return "iastore" + comment();
    }

}
