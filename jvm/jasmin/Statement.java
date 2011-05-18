package jvm.jasmin;

/**
 * Statement is a common super class for all Jasmin statements.
 */
public abstract class Statement {

    /**
     * Short one-line comment of this Statement to be output together with the
     * directive, instruction of label.
     */
    private String comment;

    /**
     * Set the comment for this Statement.
     * 
     * This method returns a reference to this object in order to simplify its
     * usage in order to be able to do:
     * 
     * {@code code.add(new Statement().setComment("string"))}
     * 
     * @param comment
     *            The comment to set
     * @return This object
     */
    public Statement setComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Returns the comment for this Statement formatted.
     * 
     * @return Formatted comment;
     */
    protected String comment() {
        if (comment != null) {
            return " \t;" + comment;
        } else {
            return "";
        }
    }

    /**
     * Returns the operand stack size change caused by this instruction.
     * 
     * @return The operand stack size change, which is 0 for everyting except
     *         Jasmin instructions.
     */
    public abstract int getOperandStackSizeChange();

    /**
     * Returns a valid Jasmin statement string representation.
     * 
     * @return A Jasmin statement string.
     */
    @Override
    public abstract String toString();

}
