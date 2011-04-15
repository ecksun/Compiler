package syntaxtree;
import syntaxtree.visitor.*;

public class MainClass {
  public Identifier i1,i2;
  public StatementList s;

  public MainClass(Identifier ai1, Identifier ai2, StatementList as) {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}

