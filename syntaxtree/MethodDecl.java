package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class MethodDecl implements Scopeable
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

    private TypeMapping scope;

    public void setScope(TypeMapping mapping)
    {
        scope = mapping;
    }

    public TypeMapping getScope()
    {
        return scope;
    }
    public boolean equals(MethodDecl other) {
        if (!methodName.equals(other.methodName))
            return false;
        if (other.args.size() != args.size())
            return false;
        for (int i = 0; i < args.size(); ++i) {
            if (!args.get(i).type.equals(other.args.get(i).type))
                return false;
        }
        return true;
    }
    
    public String toString() {
        String tmp = String.format("%s %s(", retType, methodName);
        for (int i = 0; i < args.size(); ++i) {
            if (i == args.size()-1) {
                tmp += args.get(i);
            }
            else {
                tmp += args.get(i) + ", ";
            }
        }
        tmp += ")";
        return tmp;
    }
    
    public int hashCode() {
        int hash = methodName.name.hashCode();

        for (Formal arg : args) {
            hash ^= arg.type.hashCode();
        }
        return hash;
    }

    @Override
    public String getName()
    {
        return null;
    }
    
}
