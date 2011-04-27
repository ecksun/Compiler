package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class ArrayAssign implements Statement {
    public Identifier id;
    public Exp index, value;

    public ArrayAssign(Identifier ai, Exp ae1, Exp ae2) {
        id = ai;
        index = ae1;
        value = ae2;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
