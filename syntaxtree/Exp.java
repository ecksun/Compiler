package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public abstract class Exp {
    public abstract Type accept(TypeVisitor v);

    public abstract void accept(Visitor v);
}
