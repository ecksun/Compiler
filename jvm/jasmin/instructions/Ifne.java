package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Zero-unequality conditional branch.
 */
public class Ifne extends Instruction {

    /**
     * The label to jump to if top stack value equals 0.
     */
    private String label;

    public Ifne(String label) {
        this.label = label;
    }

    @Override
    public int getOperandStackSizeChange() {
        // value =>
        return -1;
    }

    @Override
    public String toString() {
        return "ifne " + label + comment();
    }

}
