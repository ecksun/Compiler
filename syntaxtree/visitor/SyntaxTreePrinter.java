package syntaxtree.visitor;

import syntaxtree.*;

public class SyntaxTreePrinter extends DepthFirstVisitor
{
    int level = 0;
    java.io.PrintStream out;

    public SyntaxTreePrinter(java.io.PrintStream o)
    {
        out = o;
    }

    public Void visit(Program n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(MainClass n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(ClassDeclSimple n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(ClassDeclExtends n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(VarDecl n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(MethodDecl n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Formal n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(IntArrayType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(BooleanType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(IntegerType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(IdentifierType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Block n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(If n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(While n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Print n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Assign n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(ArrayAssign n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(And n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(LessThan n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Plus n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Minus n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Times n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(ArrayLookup n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(ArrayLength n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Call n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(IntegerLiteral n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(True n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(False n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(IdentifierExp n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(This n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(NewArray n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(NewObject n)
    {
        preWork(n);
        n.i.accept(this);
        postWork(n);
        return null;
    }

    public Void visit(Not n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    public Void visit(Identifier n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
        return null;
    }

    String indent()
    {
        String t = "";
        for (int i = 0; i < level; ++i)
            t += "  ";
        return t;
    }

    String nodeName(Object o)
    {
        String name = o.getClass().toString();
        int dot = name.lastIndexOf(".");
        if (dot != -1)
            name = name.substring(dot + 1);
        return name;
    }

    void preWork(Object n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name);
        ++level;
    }

    void postWork(Object n)
    {
        --level;
        out.println(indent() + ")");
    }

    void preWork(Identifier n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.name + " ]");
        ++level;
    }

    void preWork(IdentifierExp n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.s + " ]");
        ++level;
    }

    void preWork(IdentifierType n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.id + " ]");
        ++level;
    }

    void preWork(IntegerLiteral n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.i + " ]");
        ++level;
    }

    void preWork(Call n)
    {
        String name = nodeName(n);
        out.println(indent() + "(" + name + "[ " + n.method + " ]");
        ++level;
    }
    /*
     * void preWork(NewObject n)
     * {
     * String name = nodeName(n);
     * out.println(indent() + "(" + name +
     * "[ " + n.i.s + " ]");
     * ++level;
     * }
     */
}
