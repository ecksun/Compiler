package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Loads an integer value onto the stack.
 */
public class Iconst extends Instruction {

    /**
     * The integer constant value to be pushed onto the stack. Valid values are
     * -1 to 5 (inclusive).
     */
    private int value;

    /**
     * Constructs a new Iconst with the given value.
     * 
     * @param value
     *            A value between -1 and 5 (inclusive).
     */
    public Iconst(int value) {
        assert -1 <= value && value <= 5;
        this.value = value;
    }

    @Override
    public int getOperandStackSizeChange() {
        // => value
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("iconst_");

        if (value == -1) {
            str.append("m1");
        } else if (0 <= value && value <= 5) {
            str.append(value);
        } else {
            System.err.println("Invalid iconst value: " + value);
            return null;
        }

        str.append(comment());

        return str.toString();
    }

}
