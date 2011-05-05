/*
 * TODO:
 * - Fix nicer debug output
 */

package syntaxtree.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import syntaxtree.And;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDecl;
import syntaxtree.Exp;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Scopeable;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         A class containing the typemappings of a specific scope. It does also
 *         contain a reference to its parent scope, in order to be able to do
 *         lookups further up the symbol tree.
 */
public class TypeMapping {
    TypeMapping parent;
    /**
     * The root TypeMapping of the TypeMapping tree, this will be the
     * TypeMapping that handles the program scope.
     */
    public static TypeMapping programScope;
    private HashMap<String, Type> typemap;
    /**
     * Container that maps the name of a {@link Scopeable} block to the
     * corresponding scope {@link TypeMapping}.
     */
    private HashMap<String, TypeMapping> children;
    /**
     * Container that maps a method name to the corresponding list of
     * {@link MethodDecl}s.
     */
    private HashMap<String, List<MethodDecl>> methods;

    private HashMap<MethodDecl, LocalVariableIndexMapper> indexMappers;

    /**
     * Create a new TypeMapping, this will set the parent to null which is
     * probably only wanted in the top level scope, i.e. the program mapping
     * 
     * @throws VariableDupeException
     *             This should never happen, if so, internal error is incorrect
     */
    public TypeMapping() throws VariableDupeException {
        this(null, null);
        if (programScope == null) {
            programScope = this;
        }
    }

    /**
     * Create a new TypeMapping with a defined parent
     * 
     * @param obj
     * @param parent
     *            The parent to this TypeMapping
     * @throws VariableDupeException
     *             Thrown if this is a class and it already is defined
     */
    public TypeMapping(Scopeable obj, TypeMapping parent)
            throws VariableDupeException {
        System.out.println("New Scope:");
        this.parent = parent;
        typemap = new HashMap<String, Type>();
        children = new HashMap<String, TypeMapping>();
        methods = new HashMap<String, List<MethodDecl>>();
        indexMappers = new HashMap<MethodDecl, LocalVariableIndexMapper>();
        if (obj != null && obj.getName() != null) {
            parent.addChild(obj.getName(), this);
            if (obj instanceof ClassDecl) {
                typemap.put("this", new IdentifierType(
                        ((ClassDecl) obj).className));
                parent.addVariableMapping(((ClassDecl) obj).className,
                        new IdentifierType(((ClassDecl) obj).className));
            }
        }
    }

    public void addChild(String name, TypeMapping child) {
        children.put(name, child);
    }

    /**
     * Adds a new method type mapping.
     * 
     * @param decl
     *            A MethodDecl containing the new mapping to save
     * @throws VariableDupeException
     *             If the name is already used in this scope
     */
    public void addType(MethodDecl decl) throws VariableDupeException {
        List<MethodDecl> decls = methods.get(decl.methodName.name);
        if (decls == null) {
            // First occurrence of the method name -- create the list and add.
            decls = new ArrayList<MethodDecl>();
            decls.add(decl);
            methods.put(decl.methodName.name, decls);
            
            // Create a new index mapping.
            LocalVariableIndexMapper indexMapping = new LocalVariableIndexMapper(
                    this);
            // XXX Will fail if there are types of different sizes, as
            // LocalVariableIndexMapper.getIndex requires that the formals
            // are in the TypeMapping in order for it to work.
            for (Formal arg : decl.args) {
                indexMapping.getIndex(arg.name);
            }
            indexMappers.put(decl, indexMapping);
        } else {
            // Method name has already occurred -- try add declaration to list.
            boolean fail = false;
            for (int i = 0; i < decls.size(); ++i) {
                if (decls.get(i).equals(decl)) {
                    // Duplicate method definition detected -- abort!
                    fail = true;
                    break;
                }
            }
            if (fail) {
                // A conflicting method declaration has already been added.
                throw new VariableDupeException(decl.getName());
            } else {
                // Add alternative method declaration with same name as earlier.
                decls.add(decl);

                // XXX: Duplication of code in if-block above!!
                // Create a new index mapping.
                LocalVariableIndexMapper indexMapper = new LocalVariableIndexMapper(this);
                for (Formal arg : decl.args) {
                    indexMapper.getIndex(arg.name);
                }
                indexMappers.put(decl, indexMapper);
            }
        }
        System.out.println("|- " + decl);
    }

    /**
     * Add a new mapping.
     * 
     * @param decl
     *            A VarDecl containing the new mapping to save
     * @throws VariableDupeException
     *             If the name is already used in this scope
     */
    public void addType(VarDecl decl) throws VariableDupeException {
        addVariableMapping(decl.name, decl.type);
    }

    /**
     * Adds a new variable mapping.
     * 
     * @param name
     *            The variable name to use as key in the mapping.
     * @param type
     *            The type that should be mapped by the name.
     * @throws VariableDupeException
     */
    public void addVariableMapping(Identifier name, Type type)
            throws VariableDupeException {
        if (typemap.put(name.name, type) != null) {
            throw new VariableDupeException(name.name);
        }
        System.out.println("|- " + name.name + " => " + getType(name.name));
    }

    public TypeMapping getChild(String name) {
        return children.get(name);
    }

    /**
     * Get the {@link LocalVariableIndexMapper} of a {@link MethodDecl}
     * 
     * @param decl
     *            The {@link MethodDecl} to look up
     * @return A {@link LocalVariableIndexMapper} corresponding to the provided
     *         {@link MethodDecl}, or null if there is no such method.
     */
    public LocalVariableIndexMapper getIndexMapper(MethodDecl decl) {
        // TODO SymbolTableVisitor.visit(MethodDecl) antyder att indexMappningen
        // ligger i parent-scoopet, snarare än i oss själva.
        // Antingen bör vi göra om så att indexMappningen hamnar i det här
        // scoopet (vilket nog skulle kännas naturligast) eller så bör vi
        // returnera parent.get(decl) direkt.
        
        if (indexMappers.get(decl) == null) {
            return parent.getIndexMapper(decl);
        }

        return indexMappers.get(decl);
    }

    public List<MethodDecl> getMethod(String name) {
        return methods.get(name);
    }

    /**
     * Get the type of an expressions
     * 
     * @param exp
     *            The expression to look up
     * @return The type of the expression
     */
    public Type getType(Exp exp) {
        if (exp instanceof And || exp instanceof LessThan || exp instanceof Not
                || exp instanceof True || exp instanceof False) {
            return new BooleanType();
        }

        if (exp instanceof Plus || exp instanceof Minus || exp instanceof Times
                || exp instanceof ArrayLength || exp instanceof ArrayLookup
                || exp instanceof IntegerLiteral) {
            return new IntegerType();
        }

        if (exp instanceof NewArray) {
            return new IntArrayType();
        }

        if (exp instanceof NewObject) {
            return new IdentifierType(((NewObject) exp).id);
        }
        if (exp instanceof IdentifierExp) {
            return new IdentifierType(((IdentifierExp) exp).id);
        }
        if (exp instanceof This) {
            return getType("this");
        }
        if (exp instanceof Call) {
            Call call = (Call) exp;
            Type type = getType(call.obj);
            if (type instanceof IdentifierType) { // TODO should be assert
                TypeMapping objectScope = TypeMapping.programScope
                        .getChild(((IdentifierType) type).id.name);

                List<MethodDecl> matchingMethods = objectScope
                        .getMethod(call.method.name);

                for (MethodDecl method : matchingMethods) {
                    if (method.args.size() == call.args.size()) {
                        boolean match = true;
                        for (int i = 0; i < call.args.size(); ++i) {
                            if (!method.args.get(i).type.equals(call.args
                                    .get(i))) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            return method.retType;
                        }
                    }
                }

            } else {
                System.err.println("öhm, error!!");
            }
        }
        return null; // In case we missed implementing a type
    }

    /**
     * Get the type of a variable
     * 
     * @param var
     *            The variable to look up
     * @return The name of the type of the variable
     */
    public Type getType(String var) {
        Type type = typemap.get(var);
        if (type == null && parent != null) {
            return parent.getType(var);
        } else {
            return type;
        }
    }

    /**
     * Get the keys from the underlying map Should only be used for walking
     * through the types in this scope
     * 
     * @return A Set with the keys in this mapping
     */
    public Set<String> keySet() {
        return typemap.keySet();
    }
}
