package syntaxtree;

import syntaxtree.visitor.TypeMapping;

public interface Scopeable
{
    public void setScope(TypeMapping mapping);

    public TypeMapping getScope();
    
    public String getName();
}
