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
import syntaxtree.Statement;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class CodeGeneratorVisitor extends DepthFirstVisitor {
    private ClassCreator output;

    @Override
    public Void visit(And n) {
        // Visit AND expressions and let them generate code that pushes the two
        // operands to the stack.
        super.visit(n);

        output.println("iand"); // value1, value2 => result
        return null;
    }

    @Override
    public Void visit(ArrayAssign n) {
        // Visit ArrayAssign and let Identifier generate code that pushes a
        // reference to the stack, and the two expressions pushes the index and
        // value.
        super.visit(n);

        output.println("iastore"); // arrayref, index, value =>
        return null;
    }

    @Override
    public Void visit(ArrayLength n) {
        // Assume ArrayLength identifying Expressions leaves an array reference
        // on top of the stack.
        super.visit(n);

        output.println("arraylength"); // arrayref => length
        return null;
    }

    @Override
    public Void visit(ArrayLookup n) {
        // Visit ArrayLookup and assume it leaves an arrayref and index on the
        // stack.
        super.visit(n);

        output.println("iaload"); // arrayref, index => value
        return null;
    }

    @Override
    public Void visit(Assign n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Block n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(BooleanType n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Call n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ClassDeclExtends n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ClassDeclSimple n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(False n) {
        output.println("dconst_0");
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(Formal n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Identifier n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(IdentifierExp n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(IdentifierType n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(If n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(IntArrayType n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(IntegerLiteral n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(IntegerType n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(LessThan n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(MainClass mainClass) {
        output = ClassCreator.createClass(mainClass.className);
        output.println(".method public static main([Ljava/lang/String;)V");
        for (Statement statement : mainClass.statements) {
            statement.accept(this);
        }
        output.println("return");
        output.println(".end method");
        return null;
    }

    @Override
    public Void visit(MethodDecl n) {
        output.print(".method public " + n.retType + " " + n.getName() + "(");
        for (Formal arg : n.args) {
            arg.type.accept(this); // XXX Note, requires that Type doesnt print newline
        }
        output.print(")");
        n.retType.accept(this); // FIXME will this actually work? need to print V/I/Whatever
        return null;
    }

    @Override
    public Void visit(Minus n) {
        super.visit(n);
        output.println("isub");
        return null;
    }

    @Override
    public Void visit(NewArray n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(NewObject n) {

        n.id.accept(this);

        return null;
    }

    @Override
    public Void visit(Not n) {
        super.visit(n);
        output.println("ineg");
        return null;
    }

    @Override
    public Void visit(Plus n) {
        super.visit(n);
        output.println("iadd");
        return null;
    }

    @Override
    public Void visit(Print n) {

        super.visit(n);
        

        return null;
    }

    @Override
    public Void visit(Program n) {
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(This n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Times n) {

        super.visit(n);
        output.println("imul");

        return null;
    }

    @Override
    public Void visit(True n) {
        output.println("dconst_1");
        super.visit(n);

        return null;
    }

    @Override
    public Void visit(VarDecl n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(While n) {
        String expLabel = LabelCreator.getLabel();
        String endLabel = LabelCreator.getLabel();
        String stmLabel = LabelCreator.getLabel();
        output.println(expLabel + ":");
        n.exp.accept(this);

        output.println("ifne " + endLabel
                + "; if not true, jump to after while, otherwise fallthrough");
        output.println(stmLabel + ":");
        n.stm.accept(this);
        output.println("goto " + expLabel + "; Begin while again");
        output.println(endLabel + ":");

        return null;
    }
}
