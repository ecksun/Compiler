package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotField extends Directive {

    private String accessSpec;
    private String fieldName;
    private String descriptor;
    private String value;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(".field");

        if (accessSpec.length() > 0) {
            str.append(" " + accessSpec);
            if (fieldName.length() > 0) {
                str.append(" " + fieldName);
                if (descriptor.length() > 0) {
                    str.append(" " + descriptor);
                    if (value.length() > 0) {
                        str.append(" = " + value);
                    }
                }
            }
        }

        return str.toString();
    }

}
