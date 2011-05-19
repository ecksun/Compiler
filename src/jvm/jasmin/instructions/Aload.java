package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Loads a reference onto the stack from a local variable.
 */
public class Aload extends Instruction {

    /**
     * The variable number of the local variable to load the reference from.
     */
    private int varNum;

    /**
     * Constructs a new Aload with the given variable number.
     * 
     * @param varNum
     *            Number of variable to load from.
     */
    public Aload(int varNum) {
        this.varNum = varNum;
    }

    @Override
    public int getOperandStackSizeChange() {
        // => objectref
        return 1;
    }

    @Override
    public String toString() {
        return "aload" + (varNum <= 3 ? "_" : " ") + varNum + comment();
    }

}
