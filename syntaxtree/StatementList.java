package syntaxtree;

import java.util.*;

import syntaxtree.visitor.*;

public class StatementList extends Statement {
    private Vector list;
    public StatementList() {
	list = new Vector();
    }
    
    public void addElement(Statement n) {
	list.addElement(n);
   }

   public Statement elementAt(int i)  { 
      return (Statement)list.elementAt(i); 
   }

   public int size() { 
      return list.size(); 
   }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
