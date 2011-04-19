package syntaxtree;
import syntaxtree.visitor.*;

public class Print implements Statement {
  public Exp exp;

  public Print(Exp ae) {
    exp=ae; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
