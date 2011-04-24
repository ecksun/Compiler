/*
 * TODO:
 * - Fix nicer debug output
 */

package syntaxtree.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import syntaxtree.MethodDecl;
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
	public static TypeMapping programScope;
	private HashMap<String, Type> typemap;
	/**
	 * Container that maps
	 */
	private HashMap<String, TypeMapping> children;
	/**
	 * Container that maps a method name to a list of
	 * 
	 * Map that holds lists of {@link MethodDecl}s with the corresponding method
	 * names as keys.
	 */
	private HashMap<String, List<MethodDecl>> methods;

	/**
	 * Create a new TypeMapping, this will set the parent to null which is
	 * probably only wanted in the top level scope, i.e. the program mapping
	 */
	public TypeMapping() {
		this(null, null);
		if (programScope != null)
			programScope = this;
	}

	/**
	 * Create a new TypeMapping with a defined parent
	 * 
	 * @param name
	 * @param parent
	 *            The parent to this TypeMapping
	 */
	public TypeMapping(String name, TypeMapping parent) {
		System.out.println("New Scope:");
		this.parent = parent;
		typemap = new HashMap<String, Type>();
		children = new HashMap<String, TypeMapping>();
		methods = new HashMap<String, List<MethodDecl>>();
		if (name != null)
			parent.addChild(name, this);
	}

	public void addChild(String name, TypeMapping child) {
		children.put(name, child);
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
		if (type == null && parent != null)
			return parent.getType(var);
		else
			return type;
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
		if (typemap.put(decl.name.name, decl.type) != null) {
			throw new VariableDupeException(decl.name.name);
		}
		System.out.println("|- " + decl.name.name + " => "
				+ getType(decl.name.name));
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
				throw new VariableDupeException(decl.getName());
			} else {
				decls.add(decl);
			}
		}
		System.out.println("|- " + decl);
	}

	public TypeMapping getChild(String name) {
		return children.get(name);
	}

	public List<MethodDecl> getMethod(String name) {
		return methods.get(name);
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
