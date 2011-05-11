package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Create new array with given size of certain type.
 */
public class Newarray extends Instruction {

    /**
     * The type of the new array to be created.
     */
    private String arrayType;

    @Override
    public int getOperandStackSizeChange() {
        // count => arrayref
        return 0;
    }

    @Override
    public String toString() {
        return "newarray " + arrayType + comment();
    }

}
