package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class Call extends Exp
{
    public Exp obj;
    public String c = null; // class of e

    public Identifier method;
    public Deque<Exp> args;

    public Call(Exp ae, Identifier ai, Deque<Exp> ael)
    {
        obj = ae;
        method = ai;
        args = ael;
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
