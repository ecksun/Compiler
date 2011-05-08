package syntaxtree.visitor;

import java.util.ListIterator;

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
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class CodeGeneratorVisitor extends DepthFirstVisitor {
    private ClassCreator output;
    /**
     * The type mapping for the current scope. Should always be kept up to date
     * whenever changing scope.
     */
    private TypeMapping scope;

    private LocalVariableIndexMapper indexMapper;

    private String getLongName(Type type) {
        // TODO
        return type.toString();
    }

    /**
     * Gets the scope from the {@link Scopeable} block and updates the internal
     * scope reference.
     * 
     * @param block
     *            The scopeable block to update type mapping from.
     */
    private void getScope(Scopeable block) {
        scope = block.getScope();
    }

    private String getShortName(Type type) {
        if (type instanceof IntegerType || type instanceof BooleanType) {
            return "I";
        } else if (type instanceof IntArrayType) {
            return "[I";
        } else if (type instanceof IdentifierType) {
            return "L" + getLongName(type) + ";";
        }
        return null;
    }

    /**
     * Restores the type-mapping scope to the parent of the current.
     */
    private void restoreScope() {
        scope = scope.parent;
    }

    /**
     * Visits an AND expression.
     * 
     * Assumes the two operand {@link Exp}s leaves one value each on the stack,
     * when visited.
     */
    @Override
    public Void visit(And n) {
        // Visit operand expressions.
        super.visit(n);

        // Perform bitwise integer AND on the two values that have now been
        // pushed to the stack.
        output.println("iand"); // value1, value2 => result
        return null;
    }

    @Override
    public Void visit(ArrayAssign n) {
        // Visit ArrayAssign and let Identifier generate code that pushes a
        // reference to the stack, and the two expressions pushes the index and
        // value.
        super.visit(n);

        output.println("iastore"); // arrayref, index, value =>
        return null;
    }

    @Override
    public Void visit(ArrayLength n) {
        // Assume ArrayLength identifying Expressions leaves an array reference
        // on top of the stack.
        super.visit(n);

        output.println("arraylength"); // arrayref => length
        return null;
    }

    @Override
    public Void visit(ArrayLookup n) {
        // Visit ArrayLookup and assume it leaves an arrayref and index on the
        // stack.
        super.visit(n);

        output.println("iaload"); // arrayref, index => value
        return null;
    }

    @Override
    public Void visit(Assign n) {
        Type type = scope.getType(n.id.name);
        int id = indexMapper.getIndex(n.id);

        // Store value on the stack either in local variable or class field.
        if (scope.isLocal(n.id.name)) {
            n.exp.accept(this);
            if (type instanceof IdentifierType) {
                output.println("astore" + (id <= 3 ? "_" : " ") + id);
            } else if (type instanceof IntegerType || type instanceof BooleanType) {
                output.println("istore" + (id <= 3 ? "_" : " ") + id);
            }    
        } else {
            // MiniJava does only allow modification of fields in "this" object.
            // Therefore, we simply lookup the "this" type and push an object
            // reference to "this" object onto the stack. However, we must make
            // sure that the "this"-reference is placed under the value returned
            // from the expression.
            String fieldSpec = getLongName(scope.getType("this")) + "/" + n.id.name;
            String descriptor = getShortName(type);
            output.println("aload_0");
            n.exp.accept(this);
            output.println(String.format("putfield %s %s", fieldSpec, descriptor));
        }

        return null;
    }

    @Override
    public Void visit(Block n) {
        getScope(n);

        super.visit(n);

        restoreScope();

        return null;
    }

    @Override
    public Void visit(BooleanType n) {
        System.err.println("BooleanType unimplemented");
        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Call n) {
        // First add the object in which the method is to be found. 
        n.obj.accept(this);        

        // Then add the arguments, from left to right.
        ListIterator<Exp> iterator = n.args.listIterator(n.args.size());
        while (iterator.hasPrevious()) {
            iterator.previous().accept(this);
        }

        output.print("invokevirtual ");
        // TODO Might need to do s/./\// on getLongName below.
        output.print(getLongName(scope.getType(n.obj)));
        output.print("/" + n.method.name + "("); // Method name
        for (Exp arg : n.args) { // All arguments
            Type type = scope.getType(arg);
            output.print(getShortName(type));
        }
        output.println(")" + getShortName(scope.getType(n))); // The return type

        n.method.accept(this); // The actual method

        return null;
    }

    @Override
    public Void visit(ClassDeclExtends n) {
        System.err.println("ClassDeclExtends unimplemented");
        super.visit(n);

        return null;
    }

    @Override
    public Void visit(ClassDeclSimple n) {
        getScope(n);

        // Create a new class (and close previous implicitly).
        output = ClassCreator.createClass(n.className);

        // Field definitions.
        for (VarDecl varDecl : n.varDecls) {
            String fieldName = varDecl.name.name;
            String descriptor = getShortName(varDecl.type);
            output.println(String.format(".field public %s %s", fieldName, descriptor));
        }
        
        // Default constructor.
        output.addDefaultConstructor();
        
        // Visit method declarations as usual.
        for (MethodDecl methodDecl : n.methodDecls) {
            methodDecl.accept(this);
        }
        
        restoreScope();
        return null;
    }

    @Override
    public Void visit(False n) {
        output.println("iconst_0");
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(Formal n) {
        System.err.println("Formal unimplemented");
        super.visit(n);

        return null;
    }

    @Override
    public Void visit(Identifier n) {
        // TODO jag tror vi hellre vill att den här pushar en objref på stacken
        // än att skriva ut sitt eget namn (det kan den anropande visit-metoden
        // göra)
        // output.println(n.name);
        output.println("; //identifier objref for " + n);
        return null;
    }

    @Override
    public Void visit(IdentifierExp n) {
        // Load either local variable OR class field. 
        if (scope.isLocal(n.id.name)) {
            int index = indexMapper.getIndex(n.id);
            output.println("iload" + (index <= 3 ? "_" : " ") + index
                    + " ; identifier exp objref for " + n.id);    
        } else {
            Type type = scope.getType(n);
            // MiniJava does only allow modification of fields in "this" object.
            // Therefore, we simply lookup the "this" type and push an object
            // reference to "this" object onto the stack.
            String fieldSpec = getLongName(scope.getType("this")) + "/" + n.id.name;
            String descriptor = getShortName(type);
            output.println("aload_0");
            output.println(String.format("getfield %s %s", fieldSpec, descriptor));    
        }
        
        return null;
    }

    @Override
    public Void visit(IdentifierType n) {
        System.err.println("IdentifierType unimplemented");
        super.visit(n);

        return null;
    }

    @Override
    public Void visit(If n) {
        n.exp.accept(this);
        String endLabel = LabelCreator.getLabel();
        String elseLabel = LabelCreator.getLabel();
        output.println("ifeq " + elseLabel);
        n.ifStm.accept(this);
        output.println("goto " + endLabel);
        output.println(elseLabel + ":");
        n.elseStm.accept(this);
        output.println(endLabel + ":");

        return null;
    }

    @Override
    public Void visit(IntArrayType n) {
        System.err.println("IntArrayType unimplemented");
        super.visit(n);

        return null;
    }

    /**
     * Pushes value of the integer literal onto the stack.
     * 
     * @param n
     *            The integer literal to be pushed onto operation stack.
     */
    @Override
    public Void visit(IntegerLiteral n) {
        // The Jasmin instruction 'ldc' takes a constant to be pushed onto the
        // stack, in contrast to the Java bytecode instruction 'ldc', which
        // takes an index for the runtime constant pool. Behind the scenes,
        // Jasmin creates a record in that pool with the given constant, and
        // replaces the constant with the corresponding pool index.
        output.println("ldc " + n.i);

        // TODO Use bipush and sipush for numbers fitting into bytes and shorts, respectively.
        // TODO use iconst_x for x = 0, 1, 2, 3.

        return null;
    }

    @Override
    public Void visit(IntegerType n) {
        System.err.println("IntegerType unimplemented");
        super.visit(n);

        return null;
    }

    /**
     * Visits a LessThan expression and loads either 1 or 0 onto the stack,
     * representing TRUE and FALSE, respectively.
     */
    @Override
    public Void visit(LessThan n) {
        super.visit(n);
        String label = LabelCreator.getLabel();
        String endLabel = LabelCreator.getLabel();
        output.println("if_icmplt " + label + " ; if less than, goto label");
        output.println("iconst_0 ; load FALSE onto the stack");
        output.println("goto " + endLabel + " ; and finish LessThan");
        output.println(label + ":");
        output.println("iconst_1 ; load TRUE onto the stack");
        output.println(endLabel + ":");

        return null;
    }

    @Override
    public Void visit(MainClass mainClass) {
        getScope(mainClass);

        output = ClassCreator.createClass(mainClass.className);
        output.addDefaultConstructor();
        output.println(".method public static main([Ljava/lang/String;)V");
        output.println(".limit locals 1");
        output.println(".limit stack 4"); // TODO is this correct?
        for (Statement statement : mainClass.statements) {
            statement.accept(this);
        }
        output.println("return");
        output.println(".end method");

        restoreScope();
        return null;
    }

    @Override
    public Void visit(MethodDecl n) {
        getScope(n);
        
        // Update index mapper reference.
        indexMapper = scope.getIndexMapper();

        // Prepare the .method directive, incl. formal and return types.
        output.print(".method public " + n.methodName + "(");
        for (Formal arg : n.args) {
            output.print(getShortName(arg.type));
        }
        output.print(")");
        output.println(getShortName(n.retType));

        // All local variables, including formals, plus "this" variable.
        output.println(".limit locals " + (1 + n.varDecls.size() + n.args.size()));
        // FIXME Räkna maximala antalet operander som ligger på stacken i metoden.
        output.println(".limit stack 20"); 

        // Traverse the given method; first variable declarations.
        ListIterator<VarDecl> varDeclsIt = n.varDecls.listIterator(n.varDecls.size());
        while (varDeclsIt.hasPrevious()) {
            varDeclsIt.previous().accept(this);   
        }
        
        // Then statements.
        for (Statement statement : n.statements) {
            statement.accept(this);
        }
        
        // And at last the return expression.
        output.println(";ret:");
        n.returnExpression.accept(this);

        // Add appropriate return stm, depending on return type.
        if (n.retType instanceof IntegerType
                || n.retType instanceof BooleanType) {
            output.println("ireturn");
        } else if (n.retType instanceof IntArrayType
                || n.retType instanceof IdentifierType) {
            output.println("areturn");
        } else {
            // Should not happen if type checking in front-end is correct.
            System.err.println("Return expression type was not recognized: "
                    + n.retType);
        }

        output.println("; end ret");

        output.println(".end method");
        restoreScope();
        return null;
    }

    @Override
    public Void visit(Minus n) {
        super.visit(n);
        output.println("isub");
        return null;
    }

    @Override
    public Void visit(NewArray n) {
        super.visit(n);
        output.println("newarray int");
        return null;
    }

    @Override
    public Void visit(NewObject n) {
        output.println("new " + n.id.name);
        output.println("dup");
        output.println("invokespecial " + n.id.name + "/<init>()V");
        // TODO
        // n.id.accept(this);
        // output.println(); // Depending on if visit(Identifier) prints with
        // newline or not.
        return null;
    }

    @Override
    public Void visit(Not n) {
        super.visit(n);
        output.println("ineg");
        return null;
    }

    @Override
    public Void visit(Plus n) {
        super.visit(n);
        output.println("iadd");
        return null;
    }

    @Override
    public Void visit(Print n) {
        Type expType = scope.getType(n.exp);

        output.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        super.visit(n);
        output.print("invokevirtual java/io/PrintStream/println(");
        output.print(getShortName(expType));
        output.println(")V");
        return null;
    }

    @Override
    public Void visit(Program n) {
        // TODO need to do something here?
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(This n) {
        output.println("aload_0");

        return null;
    }

    @Override
    public Void visit(Times n) {
        // Visit TIMES left and right expressions so that
        // their values are pushed onto the stack.
        super.visit(n);
        output.println("imul");
        return null;
    }

    @Override
    public Void visit(True n) {
        output.println("iconst_1");
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(VarDecl n) {
        // Don't want to visit anything further down the tree from here.
        output.println("; // VarDecl for " + n.name);
        return null;
    }

    @Override
    public Void visit(While n) {
        String expLabel = LabelCreator.getLabel();
        String endLabel = LabelCreator.getLabel();
        String stmLabel = LabelCreator.getLabel();
        output.println(expLabel + ":");
        n.exp.accept(this);

        // If conditional expression is 0, go to endLabel.
        output.println("ifeq " + endLabel);
        output.println(stmLabel + ":");
        n.stm.accept(this);
        output.println("goto " + expLabel);
        output.println(endLabel + ":");

        return null;
    }
}
