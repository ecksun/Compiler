package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class ClassDeclSimple extends ClassDecl implements Scopeable
{
    public Identifier id;
    public Deque<VarDecl> varDecls;
    public Deque<MethodDecl> methodDecls;

    public ClassDeclSimple(Identifier ai, Deque<VarDecl> avl,
            Deque<MethodDecl> aml)
    {
        id = ai;
        varDecls = avl;
        methodDecls = aml;
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
        return id.name;
    }
}
