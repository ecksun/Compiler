package jvm.jasmin.instructions;

import jvm.jasmin.Instruction;

public class Getstatic extends Instruction {

    /**
     * The name and class of the field to get, e.g.
     * <code>mypackage/MyClass/my_font</code>.
     */
    private String field;

    /**
     * The field descriptor, e.g. <code>Ljava/lang/Font;</code>.
     */
    private String descriptor;

    @Override
    public int getOperandStackSizeChange() {
        // Pushes the static field value.
        return 1;
    }

    @Override
    public String toString() {
        return "getstatic " + field + " " + descriptor + comment();
    }

}
