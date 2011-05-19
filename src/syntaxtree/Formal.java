package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Formal {
    public Type type;
    public Identifier name;

    public Formal(Type at, Identifier ai) {
        type = at;
        name = ai;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s", type, name);
    }
}
