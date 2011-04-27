/**
 * 
 */
package syntaxtree.visitor;

import syntaxtree.*;

/**
 *
 */
public class DepthFirstVisitor implements Visitor<Void>
{

    @Override
    public Void visit(And and)
    {
        and.e1.accept(this);
        and.e2.accept(this);
        return null;
    }

    @Override
    public Void visit(ArrayAssign arrayAssign)
    {
        arrayAssign.id.accept(this);
        arrayAssign.index.accept(this);
        arrayAssign.value.accept(this);
        return null;
    }

    @Override
    public Void visit(ArrayLength arrayLength)
    {
        arrayLength.exp.accept(this);
        return null;
    }

    @Override
    public Void visit(Call call)
    {
        for (Exp exp : call.args) {
            exp.accept(this);
        }
        call.obj.accept(this);
        call.method.accept(this);
        return null;
    }

    @Override
    public Void visit(ArrayLookup arrayLookup)
    {
        arrayLookup.index.accept(this);
        arrayLookup.id.accept(this);
        return null;
    }

    @Override
    public Void visit(Assign assign)
    {
        assign.exp.accept(this);
        assign.id.accept(this);
        return null;
    }

    @Override
    public Void visit(Block block)
    {
        for (Statement stm : block.statements) {
            stm.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(MethodDecl methodDecl)
    {
        methodDecl.retType.accept(this);
        methodDecl.methodName.accept(this);
        for (Formal arg : methodDecl.args) {
            arg.accept(this);
        }
        for (VarDecl decl : methodDecl.varDecls)
            decl.accept(this);
        for (Statement statement : methodDecl.statements)
            statement.accept(this);
        methodDecl.returnExpression.accept(this);
        return null;
    }

    @Override
    public Void visit(MainClass mainClass)
    {
        mainClass.className.accept(this);
        mainClass.argv.accept(this);
        for (Statement statement : mainClass.statements)
            statement.accept(this);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan)
    {
        lessThan.left.accept(this);
        lessThan.right.accept(this);
        return null;
    }

    @Override
    public Void visit(IntegerType integerType)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(IntArrayType intArrayType)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(If if1)
    {
        if1.exp.accept(this);
        if1.ifStm.accept(this);
        if1.elseStm.accept(this);
        return null;
    }

    @Override
    public Void visit(IdentifierType identifierType)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(IdentifierExp identifierExp)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(Identifier identifier)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(Formal formal)
    {
        formal.type.accept(this);
        formal.name.accept(this);
        return null;
    }

    @Override
    public Void visit(False fals)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(ClassDeclSimple classDeclSimple)
    {
        classDeclSimple.className.accept(this);
        for (VarDecl varDecl : classDeclSimple.varDecls)
            varDecl.accept(this);
        for (MethodDecl methodDecl : classDeclSimple.methodDecls)
            methodDecl.accept(this);
        return null;
    }

    @Override
    public Void visit(ClassDeclExtends classDeclExtends)
    {
        // FIXME Unused
        return null;
    }

    @Override
    public Void visit(Program program)
    {
        program.main.accept(this);
        for (ClassDecl classDecl : program.classDecls)
            classDecl.accept(this);
        return null;
    }

    @Override
    public Void visit(Print print)
    {
        print.exp.accept(this);
        return null;
    }

    @Override
    public Void visit(Plus plus)
    {
        plus.left.accept(this);
        plus.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Not not)
    {
        not.exp.accept(this);
        return null;
    }

    @Override
    public Void visit(NewObject newObject)
    {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public Void visit(NewArray newArray)
    {
        newArray.exp.accept(this);
        return null;
    }

    @Override
    public Void visit(Minus minus)
    {
        minus.left.accept(this);
        minus.right.accept(this);
        return null;
    }

    @Override
    public Void visit(While whil)
    {
        whil.exp.accept(this);
        whil.stm.accept(this);
        return null;
    }

    @Override
    public Void visit(VarDecl varDecl)
    {
        varDecl.type.accept(this);
        varDecl.name.accept(this);
        return null;
    }

    @Override
    public Void visit(True tru)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(Times times)
    {
        times.left.accept(this);
        times.right.accept(this);
        return null;
    }

    @Override
    public Void visit(This ths)
    {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public Void visit(StatementList statementList)
    {
        for (Statement stm : statementList)
            stm.accept(this);
        return null;
    }

}
