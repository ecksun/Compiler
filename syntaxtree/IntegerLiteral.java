package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class IntegerLiteral extends Exp {
    public int i;

    public IntegerLiteral(String n) {
        i = Integer.parseInt(n);
    }

    /**
     * @deprecated The lexer now uses {@code IntegerLiteral(String)} so this
     *             constructor should not be used
     * @param ai
     */
    public IntegerLiteral(int ai) {
        i = ai;
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
