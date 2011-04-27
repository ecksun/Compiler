package syntaxtree;

import java.util.List;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class ClassDeclSimple extends ClassDecl implements Scopeable {
    public ClassDeclSimple(Identifier ai, List<VarDecl> avl,
            List<MethodDecl> aml) {
        super(ai, avl, aml);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
