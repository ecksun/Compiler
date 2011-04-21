package syntaxtree.visitor;

/**
 * @author Joel Petterson
 * @author Linus Wallgren
 * 
 *         Interface defining the structure of classes that can abort execution
 *         because of errors.
 */
public interface ErrorCollector
{
    /**
     * Check if an error has been generated
     * 
     * @return True if an error has occurred, false otherwise
     */
    public boolean hasErrors();

    /**
     * Return an exception detailing the error
     * 
     * @return The error exception
     */
    public Exception getError();
}
