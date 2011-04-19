package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class Program
{
    public MainClass main;
    public List<ClassDecl> classDecls;

    public Program(MainClass am, List<ClassDecl> acl)
    {
        main = am;
        classDecls = acl;
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
