package syntaxtree;

import java.util.List;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class ClassDeclExtends extends ClassDecl {
    public Identifier extending;

    public ClassDeclExtends(Identifier ai, Identifier aj, List<VarDecl> avl,
            List<MethodDecl> aml) {
        super(ai, avl, aml);
        extending = aj;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
