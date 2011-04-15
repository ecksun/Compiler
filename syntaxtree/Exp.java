package syntaxtree;
import syntaxtree.visitor.Visitor;
import syntaxtree.visitor.TypeVisitor;

public abstract class Exp {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
}
