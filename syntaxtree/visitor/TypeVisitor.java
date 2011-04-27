package syntaxtree.visitor;

import java.util.ArrayList;
import java.util.List;

import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDecl;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Exp;
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
import syntaxtree.Scopeable;
import syntaxtree.Statement;
import syntaxtree.StatementList;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class TypeVisitor implements Visitor<Type>, ErrorCollector {
    TypeMapping scope;
    boolean error;
    Exception exception;

    public TypeVisitor() {
        error = false;
    }

    /**
     * Complains with the given error message and flag this
     * {@link ErrorCollector} as having errors.
     * 
     * @param errorMessage
     *            The error message to output.
     */
    private void complain(Exception e) {
        error = true;
        exception = e;
        System.out.println(e.toString());
    }

    @Override
    public Exception getError() {
        return exception;
    }

    private void getScope(Scopeable block) {
        scope = block.getScope();
    }

    @Override
    public boolean hasErrors() {
        return error;
    }

    private void restoreScope() {
        scope = scope.parent;
    }

    @Override
    public Type visit(And and) {
        Type left = and.e1.accept(this);
        if (!(left instanceof BooleanType)) {
            complain(new WrongTypeException(BooleanType.class, left.getClass(),
                    " left hand side of and"));
        }
        Type right = and.e2.accept(this);
        if (!(right instanceof BooleanType)) {
            complain(new WrongTypeException(BooleanType.class, left.getClass(),
                    " right hand side of and"));
        }
        return new BooleanType();
    }

    @Override
    public Type visit(ArrayAssign arrayAssign) {
        Type left = scope.getType(arrayAssign.id.name);
        if (!(left instanceof IntArrayType)) {
            complain(new WrongTypeException(IntArrayType.class,
                    left.getClass(),
                    "Left hand side of integer array needs to be an integer array"));
        }
        Type index = arrayAssign.index.accept(this);
        if (!(index instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class,
                    index.getClass(), "Array index must be an integer"));
        }

        Type value = arrayAssign.index.accept(this);
        if (!(value instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class,
                    value.getClass(), "Array asignment must be an integer"));
        }

        return new IntArrayType();
    }

    @Override
    public Type visit(ArrayLength arrayLength) {
        Type exp = arrayLength.exp.accept(this);
        if (!(exp instanceof IntArrayType)) {
            complain(new WrongTypeException(IntArrayType.class, exp.getClass(),
                    "Array length needs to be applied to an array"));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(ArrayLookup arrayLookup) {
        Type id = arrayLookup.id.accept(this);
        if (!(id instanceof IntArrayType)) {
            complain(new WrongTypeException(IntArrayType.class, id.getClass(),
                    "Array identifier must be an IntArrayType."));
        }
        Type index = arrayLookup.index.accept(this);
        if (!(index instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class,
                    index.getClass(), "Array index must be an IntegerType."));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(Assign assign) {
        Type id = assign.id.accept(this);
        Type exp = assign.exp.accept(this);

        if (!(id.equals(exp))) {
            complain(new WrongTypeException(id.getClass(), exp.getClass(),
                    "Right hand side of assignment type must match left hand side variable."));
        }

        return id;
    }

    @Override
    public Type visit(Block block) {
        scope = block.getScope();
        for (Statement stm : block.statements) {
            stm.accept(this);
        }
        scope = scope.parent;
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType) {
        return booleanType;
    }

    @Override
    public Type visit(Call call) {
        Type object = call.obj.accept(this);
        TypeMapping objectScope = null;

        if (object instanceof IdentifierType) {
            objectScope = TypeMapping.programScope
                    .getChild(((IdentifierType) (object)).id.name);
            if (objectScope != null) {
                List<MethodDecl> matchingMethods = objectScope.getMethod(call.method.name);
                if (matchingMethods != null) {
                    MethodDecl methodMatch = null;

                    List<Type> types = new ArrayList<Type>();
                    for (Exp arg : call.args) {
                        types.add(arg.accept(this));
                    }

                    for (MethodDecl matchingMethod : matchingMethods) {
                        if (matchingMethod.args.size() == types.size()) {
                            boolean match = true;
                            for (int i = 0; i < call.args.size(); ++i) {
                                if (!matchingMethod.args.get(i).type
                                        .equals(types.get(i))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                methodMatch = matchingMethod;
                                break;
                            }
                        }
                    }
                    if (methodMatch != null) {
                        return methodMatch.retType; // TODO Might need to visit
                        // call.method, not sure
                    } else {
                        complain(new WrongArgumentException(call));
                    }
                } else {
                    complain(new NoSuchSymbolException(call.method));
                }
            } else {
                complain(new NoSuchSymbolException(((IdentifierType) object).id));
            }
        } else {
            complain(new WrongTypeException(IdentifierType.class, object
                    .getClass(),
                    " Object does not contain any callable methods"));
        }
        return new IdentifierType(new Identifier("Unknown Type"));
    }

    @Override
    public Type visit(ClassDeclExtends classDeclExtends) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(ClassDeclSimple classDeclSimple) {
        scope = classDeclSimple.getScope();

        for (VarDecl decl : classDeclSimple.varDecls) {
            decl.accept(this);
        }

        for (MethodDecl method : classDeclSimple.methodDecls) {
            method.accept(this);
        }

        scope = scope.parent;
        return null;
    }

    @Override
    public Type visit(False false1) {
        return new BooleanType();
    }

    @Override
    public Type visit(Formal formal) {
        return formal.type.accept(this);
    }

    @Override
    public Type visit(Identifier identifier) {
        Type type = scope.getType(identifier.name);
        if (type == null) {
            complain(new NoSuchSymbolException(identifier));
            return new IdentifierType(identifier);
        }
        return type;
    }

    @Override
    public Type visit(IdentifierExp identifierExp) {
        return identifierExp.id.accept(this);
    }

    @Override
    public Type visit(IdentifierType identifierType) {
        identifierType.id.accept(this);
        return identifierType;
    }

    @Override
    public Type visit(If if1) {
        Type exp = if1.exp.accept(this);
        if (!(exp instanceof BooleanType)) {
            complain(new WrongTypeException(BooleanType.class, exp.getClass(),
                    " if statements need a boolean expression."));
        }
        if1.ifStm.accept(this);
        if1.elseStm.accept(this);
        return null;
    }

    @Override
    public Type visit(IntArrayType intArrayType) {
        return new IntArrayType();
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral) {
        return new IntegerType();
    }

    @Override
    public Type visit(IntegerType integerType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(LessThan lessThan) {
        Type left = lessThan.left.accept(this);
        if (!(left instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    "Left hand side of less than must be of integer type."));
        }
        Type right = lessThan.right.accept(this);
        if (!(right instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    "Right hand side of less than must be of integer type."));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(MainClass mainClass) {
        scope = mainClass.getScope();

        for (Statement statement : mainClass.statements) {
            statement.accept(this);
        }

        scope = scope.parent;

        return null;
    }

    @Override
    public Type visit(MethodDecl methodDecl) {
        scope = methodDecl.getScope();

        methodDecl.retType.accept(this);

        for (Formal arg : methodDecl.args) {
            arg.accept(this);
        }

        for (VarDecl decl : methodDecl.varDecls) {
            decl.accept(this);
        }

        for (Statement statement : methodDecl.statements) {
            statement.accept(this);
        }

        Type methodType = methodDecl.retType;
        Type retType = methodDecl.returnExpression.accept(this);

        if (!methodType.equals(retType)) {
            complain(new WrongTypeException(methodType.getClass(), retType
                    .getClass(),
                    "Return expression in method must match method type definition."));
        }

        scope = scope.parent;

        return methodType;
    }

    @Override
    public Type visit(Minus minus) {
        Type left = minus.left.accept(this);
        if (!(left instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " left hand side of -"));
        }
        Type right = minus.right.accept(this);
        if (!(right instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " right hand side of -"));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(NewArray newArray) {
        Type exp = newArray.exp.accept(this);
        if (!(exp instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, exp.getClass(),
                    "Expected integer expression as array assigment index"));
        }
        return new IntArrayType();
    }

    @Override
    public Type visit(NewObject newObject) {
        return newObject.id.accept(this);
    }

    @Override
    public Type visit(Not not) {
        Type exp = not.exp.accept(this);
        if (!(exp instanceof BooleanType)) {
            complain(new WrongTypeException(BooleanType.class, exp.getClass(),
                    "Expected boolean expression in boolean negation"));
        }
        return new BooleanType();
    }

    @Override
    public Type visit(Plus plus) {
        Type left = plus.left.accept(this);
        if (!(left instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " left hand side of +"));
        }
        Type right = plus.right.accept(this);
        if (!(right instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " right hand side of +"));
        }
        return new IntegerType();
    }

    /**
     * TODO Fix Exception thrown as to take into account that there are two
     * acceptable types
     */
    @Override
    public Type visit(Print print) {
        Type exp = print.exp.accept(this);
        if (!(exp instanceof IntegerType || exp instanceof BooleanType)) {
            complain(new WrongTypeException(IntegerType.class, exp.getClass(),
                    " can only print Integer or Boolean"));
        }
        return null;
    }

    @Override
    public Type visit(Program program) {
        program.main.accept(this);
        for (ClassDecl classDecl : program.classDecls) {
            classDecl.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(StatementList statementList) {
        for (Statement stm : statementList) {
            stm.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(This this1) {
        return scope.getType("this");
    }

    @Override
    public Type visit(Times times) {
        Type left = times.left.accept(this);
        if (!(left instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " left hand side of *"));
        }
        Type right = times.right.accept(this);
        if (!(right instanceof IntegerType)) {
            complain(new WrongTypeException(IntegerType.class, left.getClass(),
                    " right hand side of *"));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(True true1) {
        return new BooleanType();
    }

    @Override
    public Type visit(VarDecl varDecl) {
        return varDecl.type.accept(this);
    }

    @Override
    public Type visit(While whil) {
        whil.exp.accept(this);
        whil.stm.accept(this);
        return null;
    }
}
