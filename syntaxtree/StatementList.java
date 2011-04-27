package syntaxtree;

import java.util.LinkedList;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class StatementList extends LinkedList<Statement> implements Statement {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public StatementList() {
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
