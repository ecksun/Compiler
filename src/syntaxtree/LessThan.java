package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class LessThan extends Exp {
    public Exp left, right;

    public LessThan(Exp ae1, Exp ae2) {
        left = ae1;
        right = ae2;
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
