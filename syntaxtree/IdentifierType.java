package syntaxtree;
import syntaxtree.visitor.*;

public class IdentifierType extends Type {
    public Identifier id;
    
    public boolean equals(Type tp)
    {
	if (! (tp instanceof IdentifierType) ) return false;
	return ((IdentifierType)tp).id.equals(id);
    }
    
  public IdentifierType(Identifier as) {
    id=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
