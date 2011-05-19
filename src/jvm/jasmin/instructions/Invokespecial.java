package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Invoke instance method; special handling for superclass, private and instance
 * initialization method invocations.
 */
public class Invokespecial extends Instruction {

    /**
     * A method specification, e.g.
     * <code>foo/baz/Myclass/myMethod(Ljava/lang/String;)V</code>.
     */
    private String methodSpec;

    /**
     * The number of arguments passed to the method; in MiniJava
     * {@link Invokespecial} is used only on default constructors, which is why
     * this will always be 0.
     */
    private static final int numArgs = 0;

    /**
     * Constructs a new {@link Invokespecial} with given method specification.
     * 
     * @param methodSpec
     *            The method to invoke.
     */
    public Invokespecial(String methodSpec) {
        this.methodSpec = methodSpec;
    }

    @Override
    public int getOperandStackSizeChange() {
        // objectref, [arg1, arg2, ...] =>
        return -(1 + numArgs);
    }

    @Override
    public String toString() {
        return "invokespecial " + methodSpec + comment();
    }

}
