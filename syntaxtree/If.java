package syntaxtree;

import syntaxtree.visitor.*;

public class If implements Statement, Scopeable
{
    public Exp exp;
    public Statement ifStm, elseStm;

    public If(Exp ae, Statement as1, Statement as2)
    {
        exp = ae;
        ifStm = as1;
        elseStm = as2;
    }

    public void accept(Visitor v)
    {
        v.visit(this);
    }

    public Type accept(TypeVisitor v)
    {
        return v.visit(this);
    }

    private TypeMapping scope;

    public void setScope(TypeMapping mapping)
    {
        scope = mapping;
    }

    public TypeMapping getScope()
    {
        return scope;
    }

    @Override
    public String getName()
    {
        return null;
    }
}
