package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class IdentifierExp extends Exp {
    // FIXME Change s from string to identifier
    public String s;

    public IdentifierExp(String as) {
        s = as;
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
