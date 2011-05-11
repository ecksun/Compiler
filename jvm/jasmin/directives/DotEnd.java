package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotEnd extends Directive {

    /**
     * The entity that is being ended by this directive, e.g. "method".
     */
    private String entity;

    @Override
    public String toString() {
        return ".end " + entity;
    }

}
