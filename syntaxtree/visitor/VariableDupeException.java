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

}
