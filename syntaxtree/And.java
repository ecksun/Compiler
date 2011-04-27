package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class And extends Exp {
    public Exp e1, e2;

    public And(Exp ae1, Exp ae2) {
        e1 = ae1;
        e2 = ae2;
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
