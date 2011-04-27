package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class IdentifierExp extends Exp {
    public Identifier id;

    public IdentifierExp(Identifier id) {
        this.id = id;
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
