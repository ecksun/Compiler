package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class BooleanType extends Type {
    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
