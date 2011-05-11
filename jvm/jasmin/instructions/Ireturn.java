package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Return integer from method.
 */
public class Ireturn extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // value => [empty]
        // TODO Does [empty] count?
        return -1;
    }

    @Override
    public String toString() {
        return "ireturn" + comment();
    }

}
