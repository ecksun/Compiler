package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class MethodDecl
{
    public Type t;
    public Identifier i;
    public List<Formal> fl;
    public List<VarDecl> vl;
    public List<Statement> sl;
    public Exp e;

    public MethodDecl(Type at, Identifier ai, List<Formal> afl,
            List<VarDecl> avl, StatementList asl, Exp ae)
    {
        t = at;
        i = ai;
        fl = afl;
        vl = avl;
        sl = asl;
        e = ae;
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
