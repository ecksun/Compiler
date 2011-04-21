/*
 * TODO:
 * * Fix nicer debug output
 * * Move TypeMapping to separate file
 * 
 */
package syntaxtree.visitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import syntaxtree.Block;
import syntaxtree.ClassDeclSimple;
import syntaxtree.If;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.VarDecl;

public class TypeCheckVisitor extends DepthFirstVisitor
{
    int level;
    TypeMapping currentScope;
    public TypeCheckVisitor() {
        level = 0;
        currentScope = new TypeMapping();
    }
    public void visit(Block block){
        newScope();
        super.visit(block);
        outOfScope();
    }
    public void visit(ClassDeclSimple classDecl) {
        newScope();
        super.visit(classDecl);
        outOfScope();
    }
    public void visit(If ifstm) {
        newScope();
        super.visit(ifstm);
        outOfScope();
    }
    public void visit(MainClass main) {
        newScope();
        super.visit(main);
        outOfScope();
    }
    public void visit(MethodDecl method) {
        currentScope.addType(method);
        newScope();
        super.visit(method);
        outOfScope();
    }
    
    private void newScope() {
        ++level;
        printLevel();
        currentScope = new TypeMapping(currentScope);
    }
    private void outOfScope()
    {
        currentScope = currentScope.parent;
        --level;
    }
    public void visit(VarDecl decl) {
        printLevel();
        currentScope.addType(decl);
    }
    
    private void printLevel() {
        for (int i = 0; i < level; ++i)
            System.out.print("  ");
    }
}
class TypeMapping {
    TypeMapping parent;
    HashMap<String, String> typemap;
    public TypeMapping() {
        this(null);
    }
    public TypeMapping(TypeMapping parent) {
        System.out.println("New Scope:");
        this.parent = parent;
        typemap = new HashMap<String, String>();
    }
    public String getType(String var) {
        String type = typemap.get(var);
        if (type == null && parent != null)
            return parent.getType(var);
        else
            return type;
    }
    public boolean addType(VarDecl decl) {
        if (typemap.containsKey(decl.name))
            return false;
        typemap.put(decl.name.name, decl.type.getClass().getName());
        System.out.println("|- " + decl.name.name + " => " + getType(decl.name.name));
        return true;
    }
    public boolean addType(MethodDecl decl) {
        if (typemap.containsKey(decl.methodName))
            return false;
        typemap.put(decl.methodName.name, decl.retType.getClass().getName());
        System.out.println("|- " + decl.methodName.name + " => " + getType(decl.methodName.name));
        return true;
    }
    // Atm only used for printing the current scope
    public Set<String> keySet() {
        return typemap.keySet();
    }
}
