package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class CustomType extends Type
{
    public Identifier id;
    
    public CustomType(Identifier id) {
        this.id = id;
    }

    @Override
    public void accept(Visitor v)
    {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v)
    {
        return v.visit(this);
    }

}
