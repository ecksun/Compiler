package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Push byte onto stack as an integer value.
 */
public class Bipush extends Instruction {

    /**
     * The byte to be pushed onto stack as an integer value.
     */
    private int value;
    
    @Override
    public int getOperandStackSizeChange() {
        // => value
        return 1;
    }

    @Override
    public String toString() {
        return "bipush " + value + comment();
    }

}
