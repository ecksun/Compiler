package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class ArrayLookup extends Exp {
    public Exp id, index;

    public ArrayLookup(Exp ae1, Exp ae2) {
        id = ae1;
        index = ae2;
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
