package syntaxtree.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import syntaxtree.Identifier;

public class ClassCreator {
    private PrintStream stream;
    private static ClassCreator currentCreator = null;

    public static ClassCreator createClass(Identifier className) {
        if (currentCreator != null) {
            currentCreator.close();
        }
        currentCreator = new ClassCreator(className);
        return currentCreator;
    }

    private ClassCreator(Identifier className) {
        try {
            stream = new PrintStream(new File(className.name + ".j"));
        } catch (FileNotFoundException e) {
            System.err
                    .println("File not found while trying to open the filewriter to the .j file");
            e.printStackTrace();
        }
    }

    private void close() {
        stream.close();
    }

    public void println(String str) {
        stream.println(str);
    }
}
