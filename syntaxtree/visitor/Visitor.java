package syntaxtree.visitor;

import syntaxtree.*;

public interface Visitor
{

    void visit(And and);

    void visit(ArrayAssign arrayAssign);

    void visit(ArrayLength arrayLength);

    void visit(Call call);

    void visit(ArrayLookup arrayLookup);

    void visit(Assign assign);

    void visit(Block block);

    void visit(BooleanType booleanType);

    void visit(MethodDecl methodDecl);

    void visit(MainClass mainClass);

    void visit(LessThan lessThan);

    void visit(IntegerType integerType);

    void visit(IntegerLiteral integerLiteral);

    void visit(IntArrayType intArrayType);

    void visit(If if1);

    void visit(IdentifierType identifierType);

    void visit(IdentifierExp identifierExp);

    void visit(Identifier identifier);

    void visit(Formal formal);

    void visit(False false1);

    void visit(ClassDeclSimple classDeclSimple);

    void visit(ClassDeclExtends classDeclExtends);

    void visit(Program program);

    void visit(Print print);

    void visit(Plus plus);

    void visit(Not not);

    void visit(NewObject newObject);

    void visit(NewArray newArray);

    void visit(Minus minus);

    void visit(While while1);

    void visit(VarDecl varDecl);

    void visit(True true1);

    void visit(Times times);

    void visit(This this1);

    void visit(StatementList statementList);

}
