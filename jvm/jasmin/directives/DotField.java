package jvm.jasmin.directives;

import jvm.jasmin.Directive;

public class DotField extends Directive {

    private String accessSpec;
    private String fieldName;
    private String descriptor;
    private String value;

    /**
     * Constructs a new .field directive.
     * 
     * @param fieldName
     *            Name of the field.
     * @param descriptor
     *            Field type descriptor.
     */
    public DotField(String accessSpec, String fieldName, String descriptor) {
        this.accessSpec = accessSpec;
        this.fieldName = fieldName;
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(".field");

        if (accessSpec != null) {
            str.append(" " + accessSpec);
            if (fieldName != null) {
                str.append(" " + fieldName);
                if (descriptor != null) {
                    str.append(" " + descriptor);
                    if (value != null) {
                        str.append(" = " + value);
                    }
                }
            }
        }

        return str.toString();
    }

}
