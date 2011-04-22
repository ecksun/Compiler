package syntaxtree;

import syntaxtree.visitor.*;

public class Formal
{
    public Type type;
    public Identifier name;

    public Formal(Type at, Identifier ai)
    {
        type = at;
        name = ai;
    }

    public void accept(Visitor v)
    {
        v.visit(this);
    }

    public Type accept(TypeVisitor v)
    {
        return v.visit(this);
    }
    
    public String toString() {
        return String.format("%s %s", type, name);
    }
}
