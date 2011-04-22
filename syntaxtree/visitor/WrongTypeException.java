package syntaxtree.visitor;

import syntaxtree.Type;

public class WrongTypeException extends CompileTimeException
{
    Class<? extends Type> expected;
    Class<? extends Type> gotten;
    String description;
    public WrongTypeException(Class<? extends Type> expected, Class<? extends Type> gotten, String description) {
        this.expected = expected;
        this.gotten = gotten;
        this.description = description;
    }
    public String toString() {
        return String.format("Expected %s but got %s: %s", expected, gotten, description);
    }
}
