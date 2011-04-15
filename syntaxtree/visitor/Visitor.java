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
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.While;

public interface Visitor
{

    Type visit(And and);

    Type visit(ArrayAssign arrayAssign);

    Type visit(ArrayLength arrayLength);

    Type visit(Call call);

    Type visit(ArrayLookup arrayLookup);

    Type visit(Assign assign);

    Type visit(Block block);

    Type visit(BooleanType booleanType);

    Type visit(MethodDecl methodDecl);

    Type visit(MainClass mainClass);

    Type visit(LessThan lessThan);

    Type visit(IntegerType integerType);

    Type visit(IntegerLiteral integerLiteral);

    Type visit(IntArrayType intArrayType);

    Type visit(If if1);

    Type visit(IdentifierType identifierType);

    Type visit(IdentifierExp identifierExp);

    Type visit(Identifier identifier);

    Type visit(Formal formal);

    Type visit(False false1);

    Type visit(ClassDeclSimple classDeclSimple);

    Type visit(ClassDeclExtends classDeclExtends);

    Type visit(Program program);

    Type visit(Print print);

    Type visit(Plus plus);

    Type visit(Not not);

    Type visit(NewObject newObject);

    Type visit(NewArray newArray);

    Type visit(Minus minus);

    Type visit(While while1);

    Type visit(VarDecl varDecl);

    Type visit(True true1);

    Type visit(Times times);

    Type visit(This this1);

}
