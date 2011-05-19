package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Assign implements Statement {
    public Identifier id;
    public Exp exp;

    public Assign(Identifier ai, Exp ae) {
        id = ai;
        exp = ae;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
