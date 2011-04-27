package syntaxtree;
import syntaxtree.visitor.*;

public class NewObject extends Exp {
  public Identifier id;
  
  public NewObject(Identifier id) {
    this.id=id;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
