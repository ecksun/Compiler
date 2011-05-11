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
     * Returns the comment for this Statement formatted.
     * 
     * @return Formatted comment;
     */
    protected String comment() {
        return comment.length() > 0 ? " \t; " + comment : "";
    }

    /**
     * Returns a valid Jasmin statement string representation.
     * 
     * @return A Jasmin statement string.
     */
    public abstract String toString();

}
