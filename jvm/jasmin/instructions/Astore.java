package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Stores a reference into a local variable.
 */
public class Astore extends Instruction {

    /**
     * The variable number, which the reference to be stored should go into.
     */
    private int varNum;
    
    @Override
    public int getOperandStackSizeChange() {
        // objref =>
        return -1;
    }

    @Override
    public String toString() {
        return "astore " + varNum + comment();
    }

}
