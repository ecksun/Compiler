package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Return void from method.
 */
public class Return extends Instruction {

    @Override
    public int getOperandStackSizeChange() {
        // => [empty]
        // TODO Does [empty] count as a stack operand?
        return 0;
    }

    @Override
    public String toString() {
        return "return" + comment();
    }

}
