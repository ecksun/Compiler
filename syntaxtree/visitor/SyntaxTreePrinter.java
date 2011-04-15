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

    public void visit(Program n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(MainClass n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(ClassDeclSimple n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(ClassDeclExtends n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(VarDecl n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(MethodDecl n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Formal n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(IntArrayType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(BooleanType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(IntegerType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(IdentifierType n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Block n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(If n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(While n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Print n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Assign n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(ArrayAssign n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(And n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(LessThan n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Plus n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Minus n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Times n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(ArrayLookup n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(ArrayLength n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Call n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(IntegerLiteral n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(True n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(False n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(IdentifierExp n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(This n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(NewArray n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(NewObject n)
    {
        preWork(n);
        n.i.accept(this);
        postWork(n);
    }

    public void visit(Not n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
    }

    public void visit(Identifier n)
    {
        preWork(n);
        super.visit(n);
        postWork(n);
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
        out.println(indent() + "(" + name + "[ " + n.s + " ]");
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
        out.println(indent() + "(" + name + "[ " + n.s + " ]");
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
        out.println(indent() + "(" + name + "[ " + n.i + " ]");
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
