package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class True extends Exp {
    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
