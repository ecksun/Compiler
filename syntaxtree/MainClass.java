package syntaxtree;

import syntaxtree.visitor.*;

public class MainClass extends ClassDecl implements Scopeable
{
    public Identifier argv;
    public MainClass(Identifier ai1, Identifier ai2, StatementList as)
    {
        super(ai1, null, null);
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
}
