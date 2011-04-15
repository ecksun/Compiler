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

public class TypeVisitor implements Visitor
{

    @Override
    public Type visit(And and)
    {
        return null;
        // TODO Auto-generated method stub
        
    }

    public Type visit(ArrayLength arrayLength)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(ArrayAssign arrayAssign)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Type visit(Assign assign)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Call call)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(ArrayLookup arrayLookup)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Block block)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(MethodDecl methodDecl)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(MainClass mainClass)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(LessThan lessThan)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(IntegerType integerType)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(IntArrayType intArrayType)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(If if1)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(IdentifierType identifierType)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(IdentifierExp identifierExp)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Identifier identifier)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Formal formal)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(False false1)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(ClassDeclSimple classDeclSimple)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(ClassDeclExtends classDeclExtends)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Program program)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Print print)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Plus plus)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Not not)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(NewObject newObject)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(NewArray newArray)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Minus minus)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(While while1)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(VarDecl varDecl)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(True true1)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(Times times)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(This this1)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
