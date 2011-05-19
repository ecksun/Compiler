package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Integer addition.
 */
public class Iadd extends Instruction {
   
    @Override
    public int getOperandStackSizeChange() {
        // value1, value2 => result
        return -1;
    }

    @Override
    public String toString() {
        return "iadd" + comment();
    }

}
