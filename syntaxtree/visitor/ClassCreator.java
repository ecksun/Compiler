package syntaxtree.visitor;

import java.util.ArrayList;

import syntaxtree.Identifier;

public class ClassCreator {
    private static ArrayList<ClassCreator> creators = new ArrayList<ClassCreator>();
    private static ClassCreator currentCreator = null; 
    public static ClassCreator createClass(Identifier className) {
        if (currentCreator != null)
            currentCreator.close();
        currentCreator = new ClassCreator(className);
        return currentCreator;
    }
    private void close() {
    }
    private ClassCreator(Identifier className) {
    }
    public void println(String str) {
        
    }
}
