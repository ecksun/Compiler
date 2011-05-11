package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Return reference from a method.
 */
public class Areturn extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // objectref => [empty]
        // TODO guess it does not matter really, 'cause stack should be empty
        // now
        return -1;
    }

    @Override
    public String toString() {
        return "areturn" + comment();
    }

}
