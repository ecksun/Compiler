package syntaxtree.visitor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jvm.jasmin.Label;
import jvm.jasmin.directives.DotClass;
import jvm.jasmin.directives.DotEnd;
import jvm.jasmin.directives.DotField;
import jvm.jasmin.directives.DotLimit;
import jvm.jasmin.directives.DotMethod;
import jvm.jasmin.directives.DotSuper;
import jvm.jasmin.instructions.Aload;
import jvm.jasmin.instructions.Areturn;
import jvm.jasmin.instructions.Arraylength;
import jvm.jasmin.instructions.Astore;
import jvm.jasmin.instructions.Bipush;
import jvm.jasmin.instructions.Dup;
import jvm.jasmin.instructions.Getfield;
import jvm.jasmin.instructions.Getstatic;
import jvm.jasmin.instructions.Goto;
import jvm.jasmin.instructions.Iadd;
import jvm.jasmin.instructions.Iaload;
import jvm.jasmin.instructions.Iand;
import jvm.jasmin.instructions.Iastore;
import jvm.jasmin.instructions.Iconst;
import jvm.jasmin.instructions.IfIcmplt;
import jvm.jasmin.instructions.Ifeq;
import jvm.jasmin.instructions.Ifne;
import jvm.jasmin.instructions.Iload;
import jvm.jasmin.instructions.Imul;
import jvm.jasmin.instructions.Invokespecial;
import jvm.jasmin.instructions.Invokevirtual;
import jvm.jasmin.instructions.Ireturn;
import jvm.jasmin.instructions.Istore;
import jvm.jasmin.instructions.Isub;
import jvm.jasmin.instructions.Ldc;
import jvm.jasmin.instructions.New;
import jvm.jasmin.instructions.Newarray;
import jvm.jasmin.instructions.Putfield;
import jvm.jasmin.instructions.Return;
import jvm.jasmin.instructions.Sipush;
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

public class CodeGeneratorVisitor extends DepthFirstVisitor<Void> {

    /**
     * The type mapping for the current scope. Should always be kept up to date
     * whenever changing scope.
     */
    private TypeMapping scope;

    /**
     * A {@link LocalVariableIndexMapper} that holds indices for local variables
     * in methods. Should always be kept up-to-date whenever changing scope to
     * another method.
     */
    private LocalVariableIndexMapper indexMapper;

    /**
     * A Jasmin type descriptor of the type that was most recently visited.
     */
    private String typeDescriptor;

    private List<jvm.jasmin.Statement> code = new LinkedList<jvm.jasmin.Statement>();

    private List<jvm.jasmin.Statement> defaultConstructor() {
        List<jvm.jasmin.Statement> stmts = new LinkedList<jvm.jasmin.Statement>();
        stmts.add(new DotMethod("public", "<init>()V"));
        stmts.add(new Aload(0));
        stmts.add(new Invokespecial("java/lang/Object/<init>()V"));
        stmts.add(new Return());
        stmts.add(new DotEnd("method"));
        return stmts;
    }

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

    /**
     * Visits the appropriate xType method, which should update the
     * typeDescriptor field that is returned afterwards.
     * 
     * @param type
     *            The type to visit and return short type descriptor name for.
     * @return The Jasmin type descriptor for the given type.
     */
    private String getShortName(Type type) {
        type.accept(this);
        return typeDescriptor;
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
        code.add(new Iand());

        return null;
    }

    @Override
    public Void visit(ArrayAssign n) {
        // Visit ArrayAssign and let Identifier generate code that pushes a
        // reference to the stack, and the two expressions pushes the index and
        // value.
        super.visit(n);

        code.add(new Iastore());

        return null;
    }

    @Override
    public Void visit(ArrayLength n) {
        // Assume ArrayLength identifying Expressions leaves an array reference
        // on top of the stack.
        super.visit(n);

        code.add(new Arraylength());

        return null;
    }

    @Override
    public Void visit(ArrayLookup n) {
        // Load array object reference and array index onto stack.
        n.id.accept(this);
        n.index.accept(this);

        // Finally, eat array reference and index, and load value onto stack.
        code.add(new Iaload());

        return null;
    }

    @Override
    public Void visit(Assign n) {
        Type type = scope.getType(n.id.name);
        int id = indexMapper.getIndex(n.id);

        // Store value on the stack either in local variable or class field.
        if (scope.isLocalVariable(n.id.name)) {
            n.exp.accept(this);
            if (type instanceof IdentifierType) {
                code.add(new Astore(id));
            } else if (type instanceof IntegerType
                    || type instanceof BooleanType) {
                code.add(new Istore(id));
            } else {
                System.err
                        .println("Type of variable to assign was unrecognized: "
                                + type.getClass());
            }
        } else {
            // MiniJava does only allow modification of fields in "this" object.
            // Therefore, we simply lookup the "this" type and push an object
            // reference to "this" object onto the stack. However, we must make
            // sure that the "this"-reference is placed under the value returned
            // from the expression.
            String fieldSpec = getLongName(scope.getType("this")) + "/"
                    + n.id.name;
            String descriptor = getShortName(type);
            code.add(new Aload(0));
            n.exp.accept(this);
            code.add(new Putfield(fieldSpec, descriptor));
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
        // We use integers to represent boolean variables.
        typeDescriptor = "I";
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

        StringBuilder methodSpec = new StringBuilder();
        // Class name.
        methodSpec.append(getLongName(scope.getType(n.obj)));

        // Method name.
        methodSpec.append("/" + n.method.name + "(");

        // Argument types.
        for (Exp arg : n.args) {
            Type type = scope.getType(arg);
            methodSpec.append(getShortName(type));
        }

        // Return type.
        methodSpec.append(")" + getShortName(scope.getType(n)));

        code.add(new Invokevirtual(methodSpec.toString(), n.args.size()));

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

        // Create a new class.
        code.add(new DotClass(n.className.name));
        code.add(new DotSuper("java/lang/Object"));

        // Field definitions.
        for (VarDecl varDecl : n.varDecls) {
            String fieldName = varDecl.name.name;
            String descriptor = getShortName(varDecl.type);
            code.add(new DotField("public", fieldName, descriptor));
        }

        // Default constructor.
        code.addAll(defaultConstructor());

        // Visit method declarations as usual.
        for (MethodDecl methodDecl : n.methodDecls) {
            methodDecl.accept(this);
        }

        restoreScope();
        return null;
    }

    @Override
    public Void visit(False n) {
        code.add(new Iconst(0));
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(Formal n) {
        return null;
    }

    /**
     * Visits an Identifier and loads the corresponding value onto the stack.
     * 
     * Assumes the given {@link Identifier} identifies a field or local
     * variable, i.e. not a class or method name.
     */
    @Override
    public Void visit(Identifier n) {
        Type type = scope.getType(n.name);

        // Load either local variable OR class field.
        if (scope.isLocalVariable(n.name)) {
            int index = indexMapper.getIndex(n);
            if (type instanceof IdentifierType) {
                code.add(new Aload(index));
            } else if (type instanceof IntegerType
                    || type instanceof BooleanType) {
                code.add(new Iload(index));
            } else {
                System.out.println("Identifier type was not recognized: "
                        + type.getClass());
            }
        } else {
            // MiniJava does only allow modification of fields in "this" object.
            // Therefore, we simply lookup the "this" type and push an object
            // reference to "this" object onto the stack.
            String fieldSpec = getLongName(scope.getType("this")) + "/"
                    + n.name;
            String descriptor = getShortName(type);
            code.add(new Aload(0));
            code.add(new Getfield(fieldSpec, descriptor));
        }

        return null;
    }

    @Override
    public Void visit(IdentifierExp n) {
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(IdentifierType n) {
        typeDescriptor = "L" + getLongName(n) + ";";
        return null;
    }

    @Override
    public Void visit(If n) {
        n.exp.accept(this);
        String endLabel = LabelCreator.getLabel();
        String elseLabel = LabelCreator.getLabel();
        code.add(new Ifeq(elseLabel));
        n.ifStm.accept(this);
        code.add(new Goto(endLabel));
        code.add(new Label(elseLabel));
        n.elseStm.accept(this);
        code.add(new Label(endLabel));
        return null;
    }

    @Override
    public Void visit(IntArrayType n) {
        typeDescriptor = "[I";
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

        if (n.i >= -1 && n.i <= 5) {
            code.add(new Iconst(n.i));
        } else if (n.i >= Byte.MIN_VALUE && n.i <= Byte.MAX_VALUE) {
            code.add(new Bipush((byte) n.i));
        } else if (n.i >= Short.MIN_VALUE && n.i <= Short.MAX_VALUE) {
            code.add(new Sipush((short) n.i));
        } else {
            // The Jasmin instruction 'ldc' takes a constant to be pushed onto
            // the
            // stack, in contrast to the Java bytecode instruction 'ldc', which
            // takes an index for the runtime constant pool. Behind the scenes,
            // Jasmin creates a record in that pool with the given constant, and
            // replaces the constant with the corresponding pool index.
            code.add(new Ldc(n.i));
        }

        return null;
    }

    @Override
    public Void visit(IntegerType n) {
        typeDescriptor = "I";
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

        // If less than is true, goto label.
        code.add(new IfIcmplt(label));
        // Otherwise, load FALSE (0) onto the stack.
        code.add(new Iconst(0));
        // Then finish the LessThan expression.
        code.add(new Goto(endLabel));
        // Add true label.
        code.add(new Label(label));
        // If comparison was true, load TRUE (1) onto the stack.
        code.add(new Iconst(1));
        // Print end label for this LessThan expression.
        code.add(new Label(endLabel));

        return null;
    }

    @Override
    public Void visit(MainClass mainClass) {
        getScope(mainClass);

        // Create class.
        code.add(new DotClass(mainClass.className.name));
        code.add(new DotSuper("java/lang/Object"));
        code.addAll(defaultConstructor());

        // Create main method.
        code.add(new DotMethod("public static", "main([Ljava/lang/String;)V"));
        // TODO Add the correct limit values.
        code.add(new DotLimit("locals", 1));
        code.add(new DotLimit("stack", 4));

        // Add statements by visiting them as usual.
        for (Statement statement : mainClass.statements) {
            statement.accept(this);
        }

        // Return and end method.
        code.add(new Return());
        code.add(new DotEnd("method"));

        restoreScope();
        return null;
    }

    @Override
    public Void visit(MethodDecl n) {
        getScope(n);

        // Update index mapper reference.
        indexMapper = scope.getIndexMapper();

        // Prepare the .method directive, incl. formal and return types.
        StringBuilder methodSpec = new StringBuilder();
        methodSpec.append(n.methodName + "(");

        ListIterator<Formal> argsIt = n.args.listIterator(n.args.size());
        Formal arg;
        while (argsIt.hasPrevious() && (arg = argsIt.previous()) != null) {
            indexMapper.getIndex(arg.name);
            methodSpec.append(getShortName(arg.type));
        }
        methodSpec.append(")");
        methodSpec.append(getShortName(n.retType));

        DotMethod methodStart = new DotMethod("public", methodSpec.toString());
        code.add(methodStart);

        // All local variables, including formals, plus "this" variable.
        code.add(new DotLimit("locals", 1 + n.varDecls.size() + n.args.size()));

        // Add stack limit in correct position. Will be updated later.
        DotLimit stackLimit = new DotLimit("stack");
        code.add(stackLimit);

        // Traverse the given method; first variable declarations.
        for (VarDecl varDecl : n.varDecls) {
            varDecl.accept(this);
        }

        // Then statements.
        for (Statement statement : n.statements) {
            statement.accept(this);
        }

        // And at last the return expression.
        n.returnExpression.accept(this);

        // Now update the maximum operand stack size limit.
        ListIterator<jvm.jasmin.Statement> stmts = code.listIterator(code
                .indexOf(methodStart));
        int maxSize = 0, currentSize = 0;
        while (stmts.hasNext()) {
            currentSize += stmts.next().getOperandStackSizeChange();
            if (currentSize > maxSize) {
                maxSize = currentSize;
            }
        }
        stackLimit.setLimit(maxSize);

        // Add appropriate return stm, depending on return type.
        if (n.retType instanceof IntegerType
                || n.retType instanceof BooleanType) {
            code.add(new Ireturn());
        } else if (n.retType instanceof IntArrayType
                || n.retType instanceof IdentifierType) {
            code.add(new Areturn());
        } else {
            // Should not happen if type checking in front-end is correct.
            System.err.println("Return expression type was not recognized: "
                    + n.retType);
        }

        code.add(new DotEnd("method"));

        restoreScope();
        return null;
    }

    @Override
    public Void visit(Minus n) {
        super.visit(n);
        code.add(new Isub());
        return null;
    }

    @Override
    public Void visit(NewArray n) {
        super.visit(n);
        code.add(new Newarray("int"));
        return null;
    }

    @Override
    public Void visit(NewObject n) {
        code.add(new New(n.id.name));
        code.add(new Dup());
        code.add(new Invokespecial(n.id.name + "/<init>()V"));
        return null;
    }

    @Override
    public Void visit(Not n) {
        super.visit(n);

        String makeFalse = LabelCreator.getLabel();
        String end = LabelCreator.getLabel();

        code.add(new Ifne(makeFalse));
        code.add(new Iconst(1));
        code.add(new Goto(end));
        code.add(new Label(makeFalse));
        code.add(new Iconst(0));
        code.add(new Label(end));

        return null;
    }

    @Override
    public Void visit(Plus n) {
        super.visit(n);
        code.add(new Iadd());
        return null;
    }

    @Override
    public Void visit(Print n) {
        Type expType = scope.getType(n.exp);
        code
                .add(new Getstatic("java/lang/System/out",
                        "Ljava/io/PrintStream;"));
        super.visit(n);
        code.add(new Invokevirtual("java/io/PrintStream/println("
                + getShortName(expType) + ")V", 1));
        return null;
    }

    @Override
    public Void visit(Program n) {
        super.visit(n);

        new ClassCreator().write(code);

        return null;
    }

    @Override
    public Void visit(This n) {
        code.add(new Aload(0));
        return null;
    }

    @Override
    public Void visit(Times n) {
        // Visit TIMES left and right expressions so that
        // their values are pushed onto the stack.
        super.visit(n);
        code.add(new Imul());
        return null;
    }

    @Override
    public Void visit(True n) {
        code.add(new Iconst(1));
        super.visit(n);
        return null;
    }

    @Override
    public Void visit(VarDecl n) {
        // Add index mapping for this variable declaration.
        indexMapper.getIndex(n.name);
        return null;
    }

    @Override
    public Void visit(While n) {
        String expLabel = LabelCreator.getLabel();
        String endLabel = LabelCreator.getLabel();
        String stmLabel = LabelCreator.getLabel();

        code.add(new Label(expLabel));

        n.exp.accept(this);

        // If conditional expression is 0, go to endLabel.
        code.add(new Ifeq(endLabel));
        code.add(new Label(stmLabel));

        n.stm.accept(this);

        code.add(new Goto(expLabel));
        code.add(new Label(endLabel));

        return null;
    }
}
