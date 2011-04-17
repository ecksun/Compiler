package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class ClassDeclSimple extends ClassDecl
{
    public Identifier i;
    public List<VarDecl> vl;
    public List<MethodDecl> ml;

    public ClassDeclSimple(Identifier ai, List<VarDecl> avl,
            List<MethodDecl> aml)
    {
        i = ai;
        vl = avl;
        ml = aml;
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
