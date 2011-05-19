package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class VarDecl {
    public Type type;
    public Identifier name;

    public VarDecl(Type at, Identifier ai) {
        type = at;
        name = ai;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
