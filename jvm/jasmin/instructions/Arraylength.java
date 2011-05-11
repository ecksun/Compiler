package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Gets the length of an array.
 */
public class Arraylength extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // arrayref => length
        return 0;
    }

    @Override
    public String toString() {
        return "arraylength" + comment();
    }

}
