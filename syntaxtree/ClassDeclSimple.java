package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class ClassDeclSimple extends ClassDecl implements Scopeable
{
    public ClassDeclSimple(Identifier ai, List<VarDecl> avl,
            List<MethodDecl> aml)
    {
        super(ai, avl, aml);
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
