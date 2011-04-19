package syntaxtree;
import syntaxtree.visitor.*;

public class ArrayLookup extends Exp {
  public Exp id,index;
  
  public ArrayLookup(Exp ae1, Exp ae2) { 
    id=ae1; index=ae2;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
