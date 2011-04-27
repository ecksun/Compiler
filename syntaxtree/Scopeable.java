package syntaxtree;

import syntaxtree.visitor.TypeMapping;

public interface Scopeable {
    public String getName();

    public TypeMapping getScope();

    public void setScope(TypeMapping mapping);
}
