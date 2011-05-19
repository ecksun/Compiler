package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

public class New extends Instruction {

    /**
     * The class name of the new object to be created, e.g.
     * <code>java/lang/String</code>.
     */
    private String className;

    public New(String className) {
        this.className = className;
    }

    @Override
    public int getOperandStackSizeChange() {
        // => objectref
        return 1;
    }

    @Override
    public String toString() {
        return "new " + className + comment();
    }

}
