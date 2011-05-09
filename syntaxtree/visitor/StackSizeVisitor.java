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

/**
 * StackSizeVisitor offers traversal of a program tree and adds the maximum
 * operand stack size to each method's scope.
 */
public class StackSizeVisitor extends DepthFirstVisitor<Integer> {
    private int maxSize;
    private int currentSize;
    private TypeMapping scope;

    /**
     * Updates the stack size and records maximum values.
     * 
     * @param pushes
     *            Number of pushes.
     * @param pops
     *            Number of pops.
     * @return The new maximum stack size.
     */
    private Integer updateSize(int pushes, int pops) {
        currentSize += pushes;
        maxSize = Math.max(currentSize, maxSize);
        currentSize -= pops;
        return maxSize;
    }

    @Override
    public Integer visit(And n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(ArrayAssign n) {
        super.visit(n);
        return updateSize(0, 3);
    }

    @Override
    public Integer visit(ArrayLength n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(ArrayLookup n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(Assign n) {
        super.visit(n);
        return updateSize(1, 2);
    }

    @Override
    public Integer visit(Block n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(BooleanType n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(Call n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(ClassDeclExtends n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(ClassDeclSimple n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(False n) {
        super.visit(n);
        return updateSize(1, 0);
    }

    @Override
    public Integer visit(Formal n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(Identifier n) {
        super.visit(n);
        if (scope != null && scope.isLocalVariable(n.name)) {
            return updateSize(1, 0);
        }
        return updateSize(2, 1); // Need at maximum 2, for getfield from this,
        // and will in such a case remove that one.
    }

    @Override
    public Integer visit(IdentifierExp n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(IdentifierType n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(If n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(IntArrayType n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(IntegerLiteral n) {
        super.visit(n);
        return updateSize(1, 0);
    }

    @Override
    public Integer visit(IntegerType n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(LessThan n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(MainClass n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(MethodDecl n) {
        // Reset counters.
        currentSize = 0;
        maxSize = 0;
        scope = n.getScope();

        // Visit all statements etc. in the MethodDecl.
        super.visit(n);

        // Now, maxOperandStackSize for this method should be up-to-date.
        scope.setMaxOperandStackSize(maxSize);

        return maxSize;
    }

    @Override
    public Integer visit(Minus n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(NewArray n) {
        super.visit(n);
        return updateSize(1, 0);
    }

    @Override
    public Integer visit(NewObject n) {
        super.visit(n);
        return updateSize(2, 1);
    }

    @Override
    public Integer visit(Not n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(Plus n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(Print n) {
        super.visit(n);
        return updateSize(1, 2);
    }

    @Override
    public Integer visit(Program n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(This n) {
        super.visit(n);
        return updateSize(1, 0);
    }

    @Override
    public Integer visit(Times n) {
        super.visit(n);
        return updateSize(0, 1);
    }

    @Override
    public Integer visit(True n) {
        super.visit(n);
        return updateSize(1, 0);
    }

    @Override
    public Integer visit(VarDecl n) {
        super.visit(n);
        return updateSize(0, 0);
    }

    @Override
    public Integer visit(While n) {
        super.visit(n);
        return updateSize(0, 1);
    }
}
