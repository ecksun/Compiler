package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Invokation of a virtual method.
 */
public class Invokevirtual extends Instruction {

    /**
     * A method specification, e.g.
     * <code>foo/baz/Myclass/myMethod(Ljava/lang/String;)V</code>.
     */
    private String methodSpec;
    
    /**
     * The number of arguments passed to the method.
     */
    private int numArgs;

    @Override
    public int getOperandStackSizeChange() {
        // objectref, [arg1, arg2, ...] =>
        return -(1 + numArgs);
    }

    @Override
    public String toString() {
        return "invokevirtual " + methodSpec + comment();
    }

}
