package jvm.jasmin;

/**
 * A Label represents a Jasmin label.
 */
public class Label extends Statement {

    /**
     * The name of this label.
     */
    String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public int getOperandStackSizeChange() {
        return 0;
    }

    @Override
    public String toString() {
        return name + ":" + comment();
    }

}
