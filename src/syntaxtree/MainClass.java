package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class MainClass extends ClassDecl implements Scopeable {
    public Identifier argv;

    public MainClass(Identifier ai1, Identifier ai2, StatementList as) {
        super(ai1, null, null);
        argv = ai2;
        statements = as;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
