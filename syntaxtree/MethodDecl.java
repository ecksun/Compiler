package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class MethodDecl implements Scopeable
{
    public Type retType;
    public Identifier methodName;
    public Deque<Formal> args;
    public Deque<VarDecl> varDecls;
    public Deque<Statement> statements;
    public Exp returnExpression;

    public MethodDecl(Type at, Identifier ai, Deque<Formal> afl,
            Deque<VarDecl> avl, StatementList asl, Exp ae)
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
        
        Iterator<Formal> otherArgs = other.args.descendingIterator();
        Iterator<Formal> myArgs = args.descendingIterator();
        
        while (myArgs.hasNext() && otherArgs.hasNext()) { 
            if (!myArgs.next().type.equals(otherArgs.next().type))
        	return false;
        }   
    	return true;
    }
    
    public String toString() {
        String tmp = String.format("%s %s(", retType, methodName);
        
        Iterator<Formal> myArgs = args.descendingIterator();
        while (myArgs.hasNext()) {
            tmp += myArgs.next() + ", ";
        }
        tmp = tmp.substring(0, tmp.length()-2);
        
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
