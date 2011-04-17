package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class Program
{
    public MainClass m;
    public List<ClassDecl> cl;

    public Program(MainClass am, List<ClassDecl> acl)
    {
        m = am;
        cl = acl;
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
