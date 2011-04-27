package syntaxtree;

import java.util.List;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Call extends Exp {
    public Exp obj;
    public String c = null; // class of e

    public Identifier method;
    public List<Exp> args;

    public Call(Exp ae, Identifier ai, List<Exp> ael) {
        obj = ae;
        method = ai;
        args = ael;
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
