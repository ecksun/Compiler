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

    /**
     * Constructs a new Astore with the given variable number.
     * 
     * @param varNum
     *            Number of the variable to store to.
     */
    public Astore(int varNum) {
        this.varNum = varNum;
    }

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
