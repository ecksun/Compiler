package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotSuper extends Directive {

    /**
     * The full name of the class, including any package names. For example
     * foo/baz/MyClass.
     */
    private String className;

    /**
     * Constructs a new DotSuper directive with given class name.
     * 
     * @param className
     *            Name of the class for this directive.
     */
    public DotSuper(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return ".super " + className;
    }

}
