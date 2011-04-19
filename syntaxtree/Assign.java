package syntaxtree;
import syntaxtree.visitor.*;

public class Assign implements Statement {
  public Identifier id;
  public Exp exp;

  public Assign(Identifier ai, Exp ae) {
    id=ai; exp=ae; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}

