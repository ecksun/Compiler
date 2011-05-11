package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Negate integer.
 */
public class Ineg extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // value => result
        return 0;
    }

    @Override
    public String toString() {
        return "ineg" + comment();
    }

}
