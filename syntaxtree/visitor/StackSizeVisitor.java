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

public class StackSizeVisitor extends DepthFirstVisitor<Integer> {
    @Override
    public Integer visit(And n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(ArrayAssign n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(ArrayLength n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(ArrayLookup n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Assign n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Block n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(BooleanType n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Call n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(ClassDeclExtends n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(ClassDeclSimple n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(False n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Formal n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Identifier n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(IdentifierExp n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(IdentifierType n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(If n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(IntArrayType n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(IntegerLiteral n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(IntegerType n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(LessThan n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(MainClass n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(MethodDecl n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Minus n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(NewArray n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(NewObject n) {
        n.id.accept(this);
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Not n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Plus n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Print n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Program n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(This n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(Times n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(True n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(VarDecl n) {
        return super.visit(n) + 0;
    }

    @Override
    public Integer visit(While n) {
        return super.visit(n) + 0;
    }
}
