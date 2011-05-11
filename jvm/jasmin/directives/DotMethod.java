package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotMethod extends Directive {

    /**
     * One of the keywords: public, private, protected, static, final,
     * synchronized, native, abstract.
     */
    private String accessSpec;

    /**
     * The name and type descriptor of the method.
     */
    private String methodSpec;

    /**
     * The code defining the body of the method.
     */
    // TODO remove, or nest statements in here?
    // private List<Statement> statements;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}
