package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Conditional branch based on less than comparison.
 */
public class IfIcmplt extends Instruction {

    /**
     * The label to jump to if comparison evaluates to true.
     */
    private String label;

    public IfIcmplt(String label) {
        this.label = label;
    }

    @Override
    public int getOperandStackSizeChange() {
        // value1, value2 =>
        return -2;
    }

    @Override
    public String toString() {
        return "if_icmplt " + label + comment();
    }

}
