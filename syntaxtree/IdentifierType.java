package syntaxtree;
import syntaxtree.visitor.Visitor;
import syntaxtree.visitor.TypeVisitor;

public class IdentifierType extends Type {
    public String s;
    
    public boolean equals(Type tp)
    {
	if (! (tp instanceof IdentifierType) ) return false;
	return ((IdentifierType)tp).s.equals(s);
    }
    
  public IdentifierType(String as) {
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
