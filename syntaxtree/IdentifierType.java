package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class IdentifierType extends Type {
    public Identifier id;

    public IdentifierType(Identifier as) {
        id = as;
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public boolean equals(Type tp) {
        if (!(tp instanceof IdentifierType)) {
            return false;
        }
        return ((IdentifierType) tp).id.equals(id);
    }
}
