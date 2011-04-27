package syntaxtree.visitor;

import syntaxtree.Call;

public class WrongArgumentException extends CompileTimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Call call;

    public WrongArgumentException(Call call) {
        this.call = call;
    }

    @Override
    public String toString() {
        return String.format("Wrong number of arguments: %s", call.toString());
    }
}
