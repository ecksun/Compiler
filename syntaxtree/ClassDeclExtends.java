package syntaxtree;
import java.util.*;

import syntaxtree.visitor.*;

public class ClassDeclExtends extends ClassDecl {
  public Identifier extending;
 
  public ClassDeclExtends(Identifier ai, Identifier aj, 
                  List<VarDecl> avl, List<MethodDecl> aml) {
      super(ai, avl, aml);
      extending = aj;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
