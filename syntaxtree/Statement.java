package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public interface Statement {
    public Type accept(TypeVisitor v);

    public void accept(Visitor v);
}
