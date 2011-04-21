/*
 * TODO:
 * * Fix nicer debug output
 */
package syntaxtree.visitor;

import syntaxtree.Block;
import syntaxtree.ClassDeclSimple;
import syntaxtree.If;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.VarDecl;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         Walk through the abstract syntax tree and create symbol tables for
 *         all possible scopes.
 */
public class SymbolTableVisitor extends DepthFirstVisitor implements
        ErrorCollector
{
    int level;
    TypeMapping currentScope;
    boolean error;
    VariableDupeException exception;

    /**
     * Create a new SymbolTableVistor.
     */
    public SymbolTableVisitor()
    {
        level = 0;
        currentScope = new TypeMapping();
        error = false;
        exception = null;
    }

    @Override
    public Exception getError()
    {
        if (exception == null)
            return null;
        return exception;
    }

    @Override
    public boolean hasErrors()
    {
        return error;
    }

    private void newScope()
    {
        ++level;
        printLevel();
        currentScope = new TypeMapping(currentScope);
    }

    private void outOfScope()
    {
        currentScope = currentScope.parent;
        --level;
    }

    private void printLevel()
    {
        for (int i = 0; i < level; ++i)
            System.out.print("  ");
    }

    public void visit(Block block)
    {
        newScope();
        super.visit(block);
        outOfScope();
    }

    public void visit(ClassDeclSimple classDecl)
    {
        newScope();
        super.visit(classDecl);
        outOfScope();
    }

    public void visit(If ifstm)
    {
        newScope();
        super.visit(ifstm);
        outOfScope();
    }

    public void visit(MainClass main)
    {
        newScope();
        super.visit(main);
        outOfScope();
    }

    public void visit(MethodDecl method)
    {
        try {
            currentScope.addType(method);
        }
        catch (VariableDupeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        newScope();
        super.visit(method);
        outOfScope();
    }

    public void visit(VarDecl decl)
    {
        printLevel();
        try {
            currentScope.addType(decl);
        }
        catch (VariableDupeException e) {
            error = true;
            e.printStackTrace();
        }
    }
}
