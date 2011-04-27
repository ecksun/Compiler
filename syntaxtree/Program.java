package syntaxtree;

import java.util.List;

import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class Program {
    public MainClass main;
    public List<ClassDecl> classDecls;

    public Program(MainClass am, List<ClassDecl> acl) {
        main = am;
        classDecls = acl;
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
