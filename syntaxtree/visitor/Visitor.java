package syntaxtree.visitor;

import syntaxtree.*;

public interface Visitor<T>
{

    T visit(And and);

    T visit(ArrayAssign arrayAssign);

    T visit(ArrayLength arrayLength);

    T visit(Call call);

    T visit(ArrayLookup arrayLookup);

    T visit(Assign assign);

    T visit(Block block);

    T visit(BooleanType booleanType);

    T visit(MethodDecl methodDecl);

    T visit(MainClass mainClass);

    T visit(LessThan lessThan);

    T visit(IntegerType integerType);

    T visit(IntegerLiteral integerLiteral);

    T visit(IntArrayType intArrayType);
    
    T visit(If if1);

    T visit(IdentifierType identifierType);

    T visit(IdentifierExp identifierExp);

    T visit(Identifier identifier);

    T visit(Formal formal);

    T visit(False false1);

    T visit(ClassDeclSimple classDeclSimple);

    T visit(ClassDeclExtends classDeclExtends);

    T visit(Program program);

    T visit(Print print);

    T visit(Plus plus);

    T visit(Not not);

    T visit(NewObject newObject);

    T visit(NewArray newArray);

    T visit(Minus minus);

    T visit(While while1);

    T visit(VarDecl varDecl);

    T visit(True true1);

    T visit(Times times);

    T visit(This this1);

    T visit(StatementList statementList);

}
