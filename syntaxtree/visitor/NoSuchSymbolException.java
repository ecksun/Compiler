package syntaxtree.visitor;

import syntaxtree.Call;
import syntaxtree.Identifier;
import syntaxtree.Type;

public class NoSuchSymbolException extends CompileTimeException
{
    Identifier id;
    public NoSuchSymbolException(Identifier method) {
        this.id = method;
    }
    public String toString() {
        return String.format("No such symbol: %s", id.toString());
    }
}
