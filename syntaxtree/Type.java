package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public abstract class Type {
    public abstract Type accept(TypeVisitor v);

    public abstract void accept(Visitor v);

    public boolean equals(Type tp) {
        return getClass().equals(tp.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
