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
import syntaxtree.StatementList;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;

public interface Visitor<T> {

    T visit(And and);

    T visit(ArrayAssign arrayAssign);

    T visit(ArrayLength arrayLength);

    T visit(ArrayLookup arrayLookup);

    T visit(Assign assign);

    T visit(Block block);

    T visit(BooleanType booleanType);

    T visit(Call call);

    T visit(ClassDeclExtends classDeclExtends);

    T visit(ClassDeclSimple classDeclSimple);

    T visit(False false1);

    T visit(Formal formal);

    T visit(Identifier identifier);

    T visit(IdentifierExp identifierExp);

    T visit(IdentifierType identifierType);

    T visit(If if1);

    T visit(IntArrayType intArrayType);

    T visit(IntegerLiteral integerLiteral);

    T visit(IntegerType integerType);

    T visit(LessThan lessThan);

    T visit(MainClass mainClass);

    T visit(MethodDecl methodDecl);

    T visit(Minus minus);

    T visit(NewArray newArray);

    T visit(NewObject newObject);

    T visit(Not not);

    T visit(Plus plus);

    T visit(Print print);

    T visit(Program program);

    T visit(StatementList statementList);

    T visit(This this1);

    T visit(Times times);

    T visit(True true1);

    T visit(VarDecl varDecl);

    T visit(While while1);

}
