package syntaxtree;

import syntaxtree.visitor.*;

public abstract class Type
{
    public boolean equals(Type tp)
    {
        return getClass().equals(tp.getClass());
    }

    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);
    public String toString() {
        return this.getClass().getSimpleName();
    }
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }
}
