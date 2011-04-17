package syntaxtree;
import syntaxtree.visitor.*;

public interface Statement {
  public void accept(Visitor v);
  public Type accept(TypeVisitor v);
}
