package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class While implements Statement {
    public Exp exp;
    public Statement stm;

    public While(Exp ae, Statement as) {
        exp = ae;
        stm = as;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
