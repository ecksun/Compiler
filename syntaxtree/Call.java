package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class Call extends Exp
{
    public Exp e;
    public String c = null; // class of e

    public Identifier i;
    public List<Exp> el;

    public Call(Exp ae, Identifier ai, List<Exp> ael)
    {
        e = ae;
        i = ai;
        el = ael;
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
