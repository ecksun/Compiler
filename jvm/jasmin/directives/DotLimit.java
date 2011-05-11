package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotLimit extends Directive {

    /**
     * The entity that this .limit directive applies to.
     */
    private String entity;

    /**
     * The actual numerical limit to impose.
     */
    private int limit;

    public DotLimit(String entity, int limit) {
        this.entity = entity;
        this.limit = limit;
    }

    public DotLimit(String string) {
        this(string, 0);
    }

    /**
     * Sets the limit value of this .limit directive.
     * 
     * @param limit
     *            The new limit value.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return ".limit " + entity + " " + limit;
    }

}
