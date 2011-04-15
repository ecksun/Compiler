package syntaxtree;
import syntaxtree.visitor.*;

public class ArrayLength extends Exp {
  public Exp exp;
  
  public ArrayLength(Exp ae) {
    exp=ae; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
