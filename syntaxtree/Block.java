package syntaxtree;

import syntaxtree.visitor.*;

public class Block implements Statement, Scopeable
{
    public StatementList statements;

    public Block(StatementList asl)
    {
        statements = asl;
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
