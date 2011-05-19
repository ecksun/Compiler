package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

/**
 * Get field value.
 */
public class Getfield extends Instruction {

    /**
     * Specification of the field to get value from. 
     */
    private String fieldSpec;
    
    /**
     * Field descriptor; the Java type descriptor of the field.
     */
    private String descriptor;
    
    public Getfield(String fieldSpec, String descriptor) {
        this.fieldSpec = fieldSpec;
        this.descriptor = descriptor;
    }

    @Override
    public int getOperandStackSizeChange() {
        // objectref => value
        return 0;
    }

    @Override
    public String toString() {
        return "getfield " + fieldSpec + " " + descriptor + comment();
    }

}
