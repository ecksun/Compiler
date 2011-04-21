package syntaxtree;
import syntaxtree.visitor.*;

public class Block implements Statement {
  public StatementList statements;

  public Block(StatementList asl) {
    statements=asl;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}

