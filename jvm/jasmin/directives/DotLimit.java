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

    @Override
    public String toString() {
        return ".limit " + entity + " " + limit;
    }

}
