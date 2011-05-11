package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotMethod extends Directive {

    /**
     * One (or more) of the keywords: public, private, protected, static, final,
     * synchronized, native, abstract.
     */
    private String accessSpec;

    /**
     * The name and type descriptor of the method.
     */
    private String methodSpec;

    /**
     * Constructs a new .method directive.
     * 
     * @param accessSpec
     *            The method visibility.
     * @param methodSpec
     *            Method specification.
     */
    public DotMethod(String accessSpec, String methodSpec) {
        this.accessSpec = accessSpec;
        this.methodSpec = methodSpec;
    }

    @Override
    public String toString() {
        return ".method " + accessSpec + " " + methodSpec + comment();
    }

}
