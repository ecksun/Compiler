package syntaxtree;
import syntaxtree.visitor.*;

public class Identifier {
  public String name;

  public Identifier(String as) { 
    name=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public boolean equals(Identifier a) {
      return a.name.equals(name);
  }

  public String toString(){
    return name;
  }
}
