package syntaxtree.visitor;

import syntaxtree.Type;

public class WrongTypeException extends CompileTimeException {

    private static final long serialVersionUID = 1L;
    Class<? extends Type> expected;
    Class<? extends Type> gotten;
    String description;

    /**
     * Constructs a new {@link WrongTypeException} with the specified
     * information about expected and given types together with a short
     * description of the error.
     * 
     * @param expected
     *            The expected type.
     * @param gotten
     *            The type that was instead found.
     * @param description
     *            A short description of the error.
     */
    public WrongTypeException(Class<? extends Type> expected,
            Class<? extends Type> gotten, String description) {
        this.expected = expected;
        this.gotten = gotten;
        this.description = description;
    }

    /**
     * Returns a string representation of this exception.
     */
    @Override
    public String toString() {
        return String.format("Expected %s but got %s: %s", expected, gotten,
                description);
    }
}
