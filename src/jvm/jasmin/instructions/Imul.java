package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Integer multiplication.
 */
public class Imul extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // value1, value1 => result
        return -1;
    }

    @Override
    public String toString() {
        return "imul" + comment();
    }

}
