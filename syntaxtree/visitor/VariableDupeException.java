package syntaxtree.visitor;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         Simple exception that is thrown when several variables in the same
 *         scope have the same name.
 */
public class VariableDupeException extends CompileTimeException
{
    private static final long serialVersionUID = 1L;
    private String variableId;

    /**
     * Constructs a new {@link VariableDupeException}.
     * 
     * @param name The name of the variable that has already been declared. 
     */
    public VariableDupeException(String name) {
	variableId = name;
    }
    
    /**
     * Returns a short description of this variable dupe exception.
     */
    public String toString() {
	return String.format("Variable %s has already been declared.", variableId); 
    }
    
}
