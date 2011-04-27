package syntaxtree.visitor;

import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class SyntaxTreePrinter extends DepthFirstVisitor {
    int level = 0;
    java.io.PrintStream out;

    public SyntaxTreePrinter(java.io.PrintStream o) {
        out = o;
    }

    String indent() {
        String t = "";
        for (int i = 0; i < level; ++i) {
            t += "  ";
        }
        return t;
    }

    String nodeName(Object o) {
        String name = o.getClass().toString();
        int dot = name.lastIndexOf(".");
        if (dot != -1) {
            name = name.substring(dot + 1);
        }
        return name;
    }

    void postWork(Object n) {
        --level;
        out.println(indent() + ")");
    }

    void preWork(Call n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.method + " ]");
        ++level;
    }

    /*
     * void preWork(NewObject n) { String name = nodeName(n);
     * out.println(indent() + "(" + name + "[ " + n.i.s + " ]"); ++level; }
     */

    void preWork(Identifier n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.name + " ]");
        ++level;
    }

    void preWork(IdentifierExp n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.s + " ]");
        ++level;
    }

    void preWork(IdentifierType n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.id + " ]");
        ++level;
    }

    void preWork(IntegerLiteral n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.i + " ]");
        ++level;
    }

    void preWork(Object n) {
        String name = nodeName(n);
        out.println(indent() + "(" + name);
        ++level;
    }

    @Override
    public Void visit(And n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(ArrayAssign n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(ArrayLength n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(ArrayLookup n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Assign n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Block n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(BooleanType n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Call n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(ClassDeclExtends n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(ClassDeclSimple n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(False n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Formal n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Identifier n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(IdentifierExp n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(IdentifierType n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(If n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(IntArrayType n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(IntegerLiteral n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(IntegerType n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(LessThan n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(MainClass n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(MethodDecl n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Minus n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(NewArray n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(NewObject n) {
        preWork(n);
        n.id.accept(this);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Not n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Plus n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Print n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Program n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(This n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(Times n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(True n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(VarDecl n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    @Override
    public Void visit(While n) {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }
}
