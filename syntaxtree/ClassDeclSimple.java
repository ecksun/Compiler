package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class ClassDeclSimple extends ClassDecl
{
    public Identifier id;
    public List<VarDecl> varDecls;
    public List<MethodDecl> methodDecls;

    public ClassDeclSimple(Identifier ai, List<VarDecl> avl,
            List<MethodDecl> aml)
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
}
