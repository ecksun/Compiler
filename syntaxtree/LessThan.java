package syntaxtree;

import syntaxtree.visitor.*;

public class LessThan extends Exp
{
    public Exp left, right;

    public LessThan(Exp ae1, Exp ae2)
    {
        left = ae1;
        right = ae2;
    }

    public void accept(Visitor v)
    {
        v.visit(this);
    }

    public Type accept(TypeVisitor v)
    {
        return v.visit(this);
    }
}
