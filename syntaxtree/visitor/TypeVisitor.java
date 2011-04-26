package syntaxtree.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import syntaxtree.*;

public class TypeVisitor implements Visitor<Type>, ErrorCollector
{
    TypeMapping scope;
    boolean error;
    Exception exception;

    public TypeVisitor()
    {
        error = false;
    }

    private void getScope(Scopeable block)
    {
        scope = block.getScope();
    }

    private void restoreScope()
    {
        scope = scope.parent;
    }

    @Override
    public Type visit(And and)
    {
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

    /**
     * Complains with the given error message and flag this
     * {@link ErrorCollector} as having errors.
     * 
     * @param errorMessage The error message to output.
     */
    private void complain(Exception e)
    {
        error = true;
        exception = e;
        System.out.println(e.toString());
    }

    @Override
    public Exception getError()
    {
        return exception;
    }

    @Override
    public boolean hasErrors()
    {
        return error;
    }

    @Override
    public Type visit(ArrayAssign arrayAssign)
    {
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
    public Type visit(ArrayLength arrayLength)
    {
        Type exp = arrayLength.exp.accept(this);
        if (!(exp instanceof IntArrayType)) {
            complain(new WrongTypeException(IntArrayType.class, exp.getClass(),
                    "Array length needs to be applied to an array"));
        }
        return new IntegerType();
    }

    @Override
    public Type visit(Call call)
    {
        Type object = call.obj.accept(this);
        TypeMapping objectScope = null;

        if (object instanceof CustomType) {
            objectScope = TypeMapping.programScope
                    .getChild(((CustomType) (object)).id.name);
            if (objectScope != null) {
                if (objectScope.getType(call.method.name) != null) {
                    List<MethodDecl> matchingMethods = objectScope.getMethod(call.method.name);
                    MethodDecl methodMatch = null;
                    
                    List<Type> types = new ArrayList<Type>();
                    for (Exp arg : call.args) {
                        types.add(arg.accept(this));
                    }
                    
                    for (MethodDecl matchingMethod : matchingMethods) {
                        if (matchingMethod.args.size() == types.size()) {
                            Iterator<Formal> matchingArgs = matchingMethod.args.descendingIterator();
                            
                            boolean match = true;
                            for (int i = 0; i < call.args.size(); ++i) {
                        	if (!matchingArgs.next().type.equals(types.get(i))) {
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
                        return methodMatch.retType; // TODO Might need to visit call.method, not sure
                    }
                    else {
                        complain(new WrongArgumentException(call));
                    }
                }
                else {
                    complain(new NoSuchSymbolException(call.method));
                }
            }
            else {
                complain(new NoSuchSymbolException(((CustomType)object).id));
            }
        }
        else {
            complain(new WrongTypeException(CustomType.class, object.getClass(), " Object does not contain any callable methods"));
        }
        return new CustomType(new Identifier("Unknown Type"));
    }

    @Override
    public Type visit(ArrayLookup arrayLookup)
    { 	
    	Type id = arrayLookup.id.accept(this);
    	if (!(id instanceof IntArrayType)) {
    		complain(new WrongTypeException(IntArrayType.class, id.getClass(), "Array identifier must be an IntArrayType."));
    	}
    	Type index = arrayLookup.index.accept(this);
    	if (!(index instanceof IntegerType)) {
    		complain(new WrongTypeException(IntegerType.class, index.getClass(), "Array index must be an IntegerType."));
    	}
    	return new IntegerType();
    }

    @Override
    public Type visit(Assign assign)
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
    	return new IntegerType();
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

    @Override
    public Type visit(StatementList statementList)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type visit(CustomType customType)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
