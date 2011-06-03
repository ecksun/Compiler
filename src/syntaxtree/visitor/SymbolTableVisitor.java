/*
 * TODO:
 * * Fix nicer debug output
 */
package syntaxtree.visitor;

import mjc.Logger;
import syntaxtree.Block;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Formal;
import syntaxtree.If;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Scopeable;
import syntaxtree.VarDecl;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         Walk through the abstract syntax tree and create symbol tables for
 *         all possible scopes.
 */
public class SymbolTableVisitor extends DepthFirstVisitor<Void> implements
        ErrorCollector {
    int level;
    TypeMapping currentScope;
    boolean error;
    VariableDupeException exception;

    /**
     * Create a new SymbolTableVistor.
     */
    public SymbolTableVisitor() {
        level = 0;
        try {
            currentScope = new TypeMapping();
        } catch (VariableDupeException e) {
            // Should never happen, see constructor javadoc.
        }
        error = false;
        exception = null;
    }

    /**
     * Complains with the given error message and flag this
     * {@link ErrorCollector} as having errors.
     * 
     * @param errorMessage
     *            The error message to output.
     */
    private void complain(String errorMessage) {
        error = true;
        System.out.println(errorMessage);
    }

    @Override
    public Exception getError() {
        if (exception == null) {
            return null;
        }
        return exception;
    }

    @Override
    public boolean hasErrors() {
        return error;
    }

    private void newScope(Scopeable obj) {
        ++level;
        printLevel();
        try {
            currentScope = new TypeMapping(obj, currentScope);
        } catch (VariableDupeException e) {
            complain(e.toString());
        }
        obj.setScope(currentScope);
    }

    private void outOfScope() {
        currentScope = currentScope.parent;
        --level;
    }

    private void printLevel() {
        for (int i = 0; i < level; ++i) {
            Logger.print("  ");
        }
    }

    @Override
    public Void visit(Block block) {
        newScope(block);
        super.visit(block);
        outOfScope();
        return null;
    }

    @Override
    public Void visit(ClassDeclSimple classDecl) {
        newScope(classDecl);
        super.visit(classDecl);
        outOfScope();
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        try {
            currentScope.addVariableMapping(formal.name, formal.type);
        } catch (VariableDupeException e) {
            complain(e.toString());
        }
        
        formal.type.accept(this);
        formal.name.accept(this);
        return null;
    }

    @Override
    public Void visit(If ifstm) {
        newScope(ifstm);
        super.visit(ifstm);
        outOfScope();
        return null;
    }

    @Override
    public Void visit(MainClass main) {
        newScope(main);
        super.visit(main);
        outOfScope();
        return null;
    }

    @Override
    public Void visit(MethodDecl method) {
        try {
            currentScope.addType(method);
        } catch (VariableDupeException e) {
            complain(e.toString());
        }
        
        newScope(method);
        super.visit(method);
        outOfScope();
        return null;
    }

    @Override
    public Void visit(VarDecl decl) {
        printLevel();
        try {
            currentScope.addType(decl);
        } catch (VariableDupeException e) {
            complain(e.toString());
        }
        return null;
    }
}
