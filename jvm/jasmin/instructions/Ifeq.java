package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Zero-equality conditional branch.
 */
public class Ifeq extends Instruction {

    /**
     * The label to jump to if top stack value equals 0.
     */
    private String label;

    public Ifeq(String label) {
        this.label = label;
    }

    @Override
    public int getOperandStackSizeChange() {
        // value =>
        return -1;
    }

    @Override
    public String toString() {
        return "ifeq " + label + comment();
    }

}
