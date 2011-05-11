package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotEnd extends Directive {

    /**
     * The entity that is being ended by this directive, e.g. "method".
     */
    private String entity;

    /**
     * Constructs a new .end directive for given entity.
     * 
     * @param entity
     *            Entity that is going to be ended.
     */
    public DotEnd(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return ".end " + entity;
    }

}
