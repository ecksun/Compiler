package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotSuper extends Directive {

    /**
     * The full name of the class, including any package names. For example
     * foo/baz/MyClass.
     */
    private String className;
    
    @Override
    public String toString() {
        return ".super " + className;
    }

}
