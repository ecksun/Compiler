/**
 * 
 */
package syntaxtree.visitor;

import syntaxtree.*;

/**
 *
 */
public class DepthFirstVisitor implements Visitor
{

    @Override
    public void visit(And and)
    {
        and.e1.accept(this);
        and.e2.accept(this);
    }

    @Override
    public void visit(ArrayAssign arrayAssign)
    {
        arrayAssign.id.accept(this);
        arrayAssign.index.accept(this);
        arrayAssign.value.accept(this);
    }

    @Override
    public void visit(ArrayLength arrayLength)
    {
        arrayLength.exp.accept(this);
    }

    @Override
    public void visit(Call call)
    {
        for (Exp exp : call.args) {
            exp.accept(this);
        }
        call.obj.accept(this);
        call.method.accept(this);
    }

    @Override
    public void visit(ArrayLookup arrayLookup)
    {
        arrayLookup.index.accept(this);
        arrayLookup.id.accept(this);
    }

    @Override
    public void visit(Assign assign)
    {
        assign.exp.accept(this);
        assign.id.accept(this);
    }

    @Override
    public void visit(Block block)
    {
        for (Statement stm : block.statements) {
            stm.accept(this);
        }
    }

    @Override
    public void visit(BooleanType booleanType)
    {
        // Nothing to be done, this is a leaf
    }

    @Override
    public void visit(MethodDecl methodDecl)
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
    }

    @Override
    public void visit(MainClass mainClass)
    {
        mainClass.className.accept(this);
        mainClass.argv.accept(this);
        for (Statement statement : mainClass.statements)
            statement.accept(this);
    }

    @Override
    public void visit(LessThan lessThan)
    {
        lessThan.left.accept(this);
        lessThan.right.accept(this);
    }

    @Override
    public void visit(IntegerType integerType)
    {
        // Nothing to be done, this is a leaf
    }

    @Override
    public void visit(IntegerLiteral integerLiteral)
    {
        // Nothing to be done, this is a leaf        
    }

    @Override
    public void visit(IntArrayType intArrayType)
    {
        // Nothing to be done, this is a leaf        
    }

    @Override
    public void visit(If if1)
    {
        if1.exp.accept(this);
        if1.ifStm.accept(this);
        if1.elseStm.accept(this);
    }

    @Override
    public void visit(IdentifierType identifierType)
    {
        // Nothing to be done, this is a leaf
    }

    @Override
    public void visit(IdentifierExp identifierExp)
    {
        // Nothing to be done, this is a leaf        
    }

    @Override
    public void visit(Identifier identifier)
    {
        // Nothing to be done, this is a leaf        
    }

    @Override
    public void visit(Formal formal)
    {
        formal.type.accept(this);
        formal.name.accept(this);
    }

    @Override
    public void visit(False fals)
    {
        // Nothing to be done, this is a leaf
    }

    @Override
    public void visit(ClassDeclSimple classDeclSimple)
    {
        classDeclSimple.id.accept(this);
        for (VarDecl varDecl : classDeclSimple.varDecls)
            varDecl.accept(this);
        for (MethodDecl methodDecl : classDeclSimple.methodDecls)
            methodDecl.accept(this);
    }

    @Override
    public void visit(ClassDeclExtends classDeclExtends)
    {
        // FIXME Unused
    }

    @Override
    public void visit(Program program)
    {
        program.main.accept(this);
        for (ClassDecl classDecl : program.classDecls)
            classDecl.accept(this);
    }

    @Override
    public void visit(Print print)
    {
        print.exp.accept(this);
    }

    @Override
    public void visit(Plus plus)
    {
        plus.left.accept(this);
        plus.right.accept(this);
    }

    @Override
    public void visit(Not not)
    {
        not.exp.accept(this);
    }

    @Override
    public void visit(NewObject newObject)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(NewArray newArray)
    {
        newArray.exp.accept(this);
    }

    @Override
    public void visit(Minus minus)
    {
        minus.left.accept(this);
        minus.right.accept(this);
    }

    @Override
    public void visit(While whil)
    {
        whil.exp.accept(this);
        whil.stm.accept(this);
    }

    @Override
    public void visit(VarDecl varDecl)
    {
        varDecl.type.accept(this);
        varDecl.name.accept(this);
    }

    @Override
    public void visit(True tru)
    {
        // Nothing to be done, this is a leaf        
    }

    @Override
    public void visit(Times times)
    {
        times.left.accept(this);
        times.right.accept(this);
    }

    @Override
    public void visit(This ths)
    {
        // Nothing to be done, this is a leaf
    }

    @Override
    public void visit(StatementList statementList) {
        for (Statement stm : statementList)
            stm.accept(this);
    }

}
