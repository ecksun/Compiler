package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class StatementList extends ArrayList<Statement> implements Statement
{
    private ArrayList<Statement> list;

    public StatementList()
    {
        list = new ArrayList<Statement>();
    }

    public void accept(Visitor v)
    {
        v.visit(this);
    }

    public Type accept(TypeVisitor v)
    {
        return v.visit(this);
    }
}
