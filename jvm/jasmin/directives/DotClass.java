package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotClass extends Directive {

    /**
     * The name of the class that this directive describes.
     */
    private String name;

    /**
     * Constructs a new .class directive.
     * 
     * @param name
     *            Name of the class.
     */
    public DotClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ".class " + name;
    }

}
