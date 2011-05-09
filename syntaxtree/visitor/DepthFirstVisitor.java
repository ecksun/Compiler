/**
 * 
 */
package syntaxtree.visitor;

import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDecl;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Exp;
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
import syntaxtree.StatementList;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;

/**
 *
 */
public class DepthFirstVisitor<T> implements Visitor<T> {

    @Override
    public T visit(And and) {
        and.e1.accept(this);
        and.e2.accept(this);
        return null;
    }

    @Override
    public T visit(ArrayAssign arrayAssign) {
        arrayAssign.id.accept(this);
        arrayAssign.index.accept(this);
        arrayAssign.value.accept(this);
        return null;
    }

    @Override
    public T visit(ArrayLength arrayLength) {
        arrayLength.exp.accept(this);
        return null;
    }

    @Override
    public T visit(ArrayLookup arrayLookup) {
        arrayLookup.index.accept(this);
        arrayLookup.id.accept(this);
        return null;
    }

    @Override
    public T visit(Assign assign) {
        assign.exp.accept(this);
        assign.id.accept(this);
        return null;
    }

    @Override
    public T visit(Block block) {
        for (Statement stm : block.statements) {
            stm.accept(this);
        }
        return null;
    }

    @Override
    public T visit(BooleanType booleanType) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(Call call) {
        for (Exp exp : call.args) {
            exp.accept(this);
        }
        call.obj.accept(this);
        call.method.accept(this);
        return null;
    }

    @Override
    public T visit(ClassDeclExtends classDeclExtends) {
        // FIXME Unused
        return null;
    }

    @Override
    public T visit(ClassDeclSimple classDeclSimple) {
        classDeclSimple.className.accept(this);
        for (VarDecl varDecl : classDeclSimple.varDecls) {
            varDecl.accept(this);
        }
        for (MethodDecl methodDecl : classDeclSimple.methodDecls) {
            methodDecl.accept(this);
        }
        return null;
    }

    @Override
    public T visit(False fals) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(Formal formal) {
        formal.type.accept(this);
        formal.name.accept(this);
        return null;
    }

    @Override
    public T visit(Identifier identifier) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(IdentifierExp identifierExp) {
        identifierExp.id.accept(this);
        return null;
    }

    @Override
    public T visit(IdentifierType identifierType) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(If if1) {
        if1.exp.accept(this);
        if1.ifStm.accept(this);
        if1.elseStm.accept(this);
        return null;
    }

    @Override
    public T visit(IntArrayType intArrayType) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(IntegerLiteral integerLiteral) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(IntegerType integerType) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(LessThan lessThan) {
        lessThan.left.accept(this);
        lessThan.right.accept(this);
        return null;
    }

    @Override
    public T visit(MainClass mainClass) {
        mainClass.className.accept(this);
        mainClass.argv.accept(this);
        for (Statement statement : mainClass.statements) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(MethodDecl methodDecl) {
        methodDecl.retType.accept(this);
        methodDecl.methodName.accept(this);
        for (Formal arg : methodDecl.args) {
            arg.accept(this);
        }
        for (VarDecl decl : methodDecl.varDecls) {
            decl.accept(this);
        }
        for (Statement statement : methodDecl.statements) {
            statement.accept(this);
        }
        methodDecl.returnExpression.accept(this);
        return null;
    }

    @Override
    public T visit(Minus minus) {
        minus.left.accept(this);
        minus.right.accept(this);
        return null;
    }

    @Override
    public T visit(NewArray newArray) {
        newArray.exp.accept(this);
        return null;
    }

    @Override
    public T visit(NewObject newObject) {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public T visit(Not not) {
        not.exp.accept(this);
        return null;
    }

    @Override
    public T visit(Plus plus) {
        plus.left.accept(this);
        plus.right.accept(this);
        return null;
    }

    @Override
    public T visit(Print print) {
        print.exp.accept(this);
        return null;
    }

    @Override
    public T visit(Program program) {
        program.main.accept(this);
        for (ClassDecl classDecl : program.classDecls) {
            classDecl.accept(this);
        }
        return null;
    }

    @Override
    public T visit(StatementList statementList) {
        for (Statement stm : statementList) {
            stm.accept(this);
        }
        return null;
    }

    @Override
    public T visit(This ths) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(Times times) {
        times.left.accept(this);
        times.right.accept(this);
        return null;
    }

    @Override
    public T visit(True tru) {
        // Nothing to be done, this is a leaf
        return null;
    }

    @Override
    public T visit(VarDecl varDecl) {
        varDecl.type.accept(this);
        varDecl.name.accept(this);
        return null;
    }

    @Override
    public T visit(While whil) {
        whil.exp.accept(this);
        whil.stm.accept(this);
        return null;
    }

}
