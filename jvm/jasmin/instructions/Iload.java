package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Load integer value from a local variable.
 */
public class Iload extends Instruction {

    /**
     * The variable number that value should be loaded from.
     */
    private int varNum;

    @Override
    public int getOperandStackSizeChange() {
        // => value
        return 1;
    }

    @Override
    public String toString() {
        return "iload" + (varNum <= 3 ? "_" : " ") + varNum + comment();
    }

}
