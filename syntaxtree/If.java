package syntaxtree;

import syntaxtree.visitor.TypeMapping;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class If implements Statement, Scopeable {
    public Exp exp;
    public Statement ifStm, elseStm;

    private TypeMapping scope;

    public If(Exp ae, Statement as1, Statement as2) {
        exp = ae;
        ifStm = as1;
        elseStm = as2;
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
