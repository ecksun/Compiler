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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(ArrayLookup arrayLookup)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Assign assign)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Block block)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(BooleanType booleanType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(MethodDecl methodDecl)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(MainClass mainClass)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(LessThan lessThan)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(IntegerType integerType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(IntegerLiteral integerLiteral)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(IntArrayType intArrayType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(If if1)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(IdentifierType identifierType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(IdentifierExp identifierExp)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Identifier identifier)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Formal formal)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(False false1)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(ClassDeclSimple classDeclSimple)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(ClassDeclExtends classDeclExtends)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Program program)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Print print)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Plus plus)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Not not)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(NewObject newObject)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(NewArray newArray)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Minus minus)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(While while1)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(VarDecl varDecl)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(True true1)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Times times)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(This this1)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(StatementList statementList) {
	// TODO Auto-generated method stub
	
    }

}
