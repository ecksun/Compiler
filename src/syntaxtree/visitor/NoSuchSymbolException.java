package syntaxtree.visitor;

import syntaxtree.Identifier;

public class NoSuchSymbolException extends CompileTimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Identifier id;

    public NoSuchSymbolException(Identifier id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("No such symbol: %s", id.toString());
    }
}
