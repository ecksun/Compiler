package syntaxtree;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Identifier {
    public String name;

    public Identifier(String as) {
        name = as;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public boolean equals(Identifier a) {
        return a.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
