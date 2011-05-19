package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Duplicate value on top of the stack.
 */
public class Dup extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // value => value, value
        return 1;
    }

    @Override
    public String toString() {
        return "dup" + comment();
    }

}
