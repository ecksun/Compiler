package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class MethodDecl
{
    public Type retType;
    public Identifier methodName;
    public List<Formal> args;
    public List<VarDecl> varDecls;
    public List<Statement> statements;
    public Exp returnExpression;

    public MethodDecl(Type at, Identifier ai, List<Formal> afl,
            List<VarDecl> avl, StatementList asl, Exp ae)
    {
        retType = at;
        methodName = ai;
        args = afl;
        varDecls = avl;
        statements = asl;
        returnExpression = ae;
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
