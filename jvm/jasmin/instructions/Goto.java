package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Go to a label.
 */
public class Goto extends Instruction {

    /**
     * The label to go to.
     */
    private String label;

    public Goto(String label) {
        this.label = label;
    }

    @Override
    public int getOperandStackSizeChange() {
        // [no change]
        return 0;
    }

    @Override
    public String toString() {
        return "goto " + label + comment();
    }

}
