package syntaxtree.visitor;

import syntaxtree.Call;

public class WrongArgumentException extends CompileTimeException
{
    Call call;
    public WrongArgumentException(Call call) {
        this.call = call;
    }
    public String toString() {
        return String.format("Wrong number of arguments: %s", call.toString());
    }
}
