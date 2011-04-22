package syntaxtree;

import syntaxtree.visitor.*;

public class MainClass implements Scopeable
{
    public Identifier className, argv;
    public StatementList statements;

    public MainClass(Identifier ai1, Identifier ai2, StatementList as)
    {
        className = ai1;
        argv = ai2;
        statements = as;
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
        return className.name;
    }
}
