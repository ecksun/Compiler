package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Sets a field to a value.
 */
public class Putfield extends Instruction {

    /**
     * The field to be set.
     */
    private String fieldSpec;

    /**
     * The Java type of the field.
     */
    private String descriptor;

    /**
     * Constructs a new Putfield instruction.
     * 
     * @param fieldSpec
     *            The field to put into.
     * @param descriptor
     *            Field type descriptor.
     */
    public Putfield(String fieldSpec, String descriptor) {
        this.fieldSpec = fieldSpec;
        this.descriptor = descriptor;
    }

    @Override
    public int getOperandStackSizeChange() {
        // objectref, value =>
        return -2;
    }

    @Override
    public String toString() {
        return "putfield " + fieldSpec + " " + descriptor + comment();
    }

}
