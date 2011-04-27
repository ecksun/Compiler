package syntaxtree;

import syntaxtree.visitor.TypeMapping;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Block implements Statement, Scopeable {
    public StatementList statements;

    private TypeMapping scope;

    public Block(StatementList asl) {
        statements = asl;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String getName() {
        return null;
    }

    public TypeMapping getScope() {
        return scope;
    }

    public void setScope(TypeMapping mapping) {
        scope = mapping;
    }
}
