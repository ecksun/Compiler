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

public class CodeGeneratorVisitor extends DepthFirstVisitor {
    private ClassCreator output;

    @Override
    public Void visit(And n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ArrayAssign n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ArrayLength n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ArrayLookup n) {

        super.visit(n);

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
    public Void visit(MainClass n) {
        output = ClassCreator.createClass(n.className);
        output.println(".method public static main([Ljava/lang/String;)V");
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(MethodDecl n) {

        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Minus n) {

        super.visit(n);

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

        return null;
    }

    @Override
    public Void visit(Plus n) {

        super.visit(n);

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

        return null;
    }

    @Override
    public Void visit(True n) {

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

        super.visit(n);

        return null;
    }
}
