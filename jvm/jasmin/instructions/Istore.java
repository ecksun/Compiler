package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Store integer value into local variable.
 */
public class Istore extends Instruction {

    /**
     * The variable number into which the store operation should put the value
     * being stored.
     */
    private int varNum;

    /**
     * Constructs a new Istore with the given variable number.
     * 
     * @param varNum
     *            Number of variable to store into.
     */
    public Istore(int varNum) {
        this.varNum = varNum;
    }

    @Override
    public int getOperandStackSizeChange() {
        // value =>
        return -1;
    }

    @Override
    public String toString() {
        return "istore" + (varNum <= 3 ? "_" : " ") + varNum + comment();
    }

}
