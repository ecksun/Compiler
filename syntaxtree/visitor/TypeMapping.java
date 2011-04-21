/*
 * TODO:
 * - Fix nicer debug output
 */

package syntaxtree.visitor;

import java.util.HashMap;
import java.util.Set;

import syntaxtree.MethodDecl;
import syntaxtree.VarDecl;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         A class containing the typemappings of a specific scope. It does also
 *         contain a reference to its parent scope, in order to be able to do
 *         lookups further up the symbol tree.
 */
public class TypeMapping
{
    TypeMapping parent;
    HashMap<String, String> typemap;

    /**
     * Create a new TypeMapping, this will set the parent to null which is
     * probably only wanted in the top level scope, i.e. the program mapping
     */
    public TypeMapping()
    {
        this(null);
    }

    /**
     * Create a new TypeMapping with a defined parent
     * 
     * @param parent The parent to this TypeMapping
     */
    public TypeMapping(TypeMapping parent)
    {
        System.out.println("New Scope:");
        this.parent = parent;
        typemap = new HashMap<String, String>();
    }

    /**
     * Get the type of a variable
     * 
     * @param var The variable to look up
     * @return The name of the type of the variable
     */
    public String getType(String var)
    {
        String type = typemap.get(var);
        if (type == null && parent != null)
            return parent.getType(var);
        else
            return type;
    }

    /**
     * Add a new mapping.
     * 
     * @param decl A VarDecl containing the new mapping to save
     * @throws VariableDupeException If the name is already used in this scope
     */
    public void addType(VarDecl decl) throws VariableDupeException
    {
	String variableName = decl.name.name;
        if (typemap.put(variableName, decl.type.getClass().getName()) != null) {
            throw new VariableDupeException(variableName);
        }
        System.out.println("|- " + variableName + " => "
                + getType(variableName));
    }

    /**
     * Add a new mapping.
     * 
     * @param decl A MethodDecl containing the new mapping to save
     * @throws VariableDupeException If the name is already used in this scope
     */
    public void addType(MethodDecl decl) throws VariableDupeException
    {
        String variableName = decl.methodName.name;         
        if (typemap.put(variableName, decl.retType.getClass().getName()) != null) {
            throw new VariableDupeException(variableName);
        }
        System.out.println("|- " + variableName + " => "
                + getType(variableName));
    }

    /**
     * Get the keys from the underlying map
     * Should only be used for walking through the types in this scope
     * 
     * @return A Set with the keys in this mapping
     */
    public Set<String> keySet()
    {
        return typemap.keySet();
    }
}
