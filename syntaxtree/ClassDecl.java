package syntaxtree;

import java.util.List;

import syntaxtree.visitor.TypeMapping;
import syntaxtree.visitor.Visitor;

public abstract class ClassDecl implements Scopeable {
    public Identifier className;
    public StatementList statements;
    private TypeMapping scope;
    public List<VarDecl> varDecls;
    public List<MethodDecl> methodDecls;

    public ClassDecl(Identifier ai, List<VarDecl> avl, List<MethodDecl> aml) {
        className = ai;
        varDecls = avl;
        methodDecls = aml;
    }

    public abstract void accept(Visitor v);

    @Override
    public String getName() {
        return className.name;
    }

    public TypeMapping getScope() {
        return scope;
    }

    public void setScope(TypeMapping mapping) {
        scope = mapping;
    }
}
