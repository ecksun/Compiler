package syntaxtree.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import jvm.jasmin.Statement;
import jvm.jasmin.directives.DotClass;

public class ClassCreator {
    private PrintStream stream;

    public ClassCreator() {
        stream = null;
    }

    private void close() {
        if (stream != null) {
            stream.close();
        }
    }

    private void open(String className) {
        try {
            stream = new PrintStream(new File(className + ".j"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void println(Object stm) {
        stream.println(stm);
    }

    public void write(List<Statement> code) {
        for (Statement stm : code) {
            if (stm instanceof DotClass) {
                // Create new print stream (and close old, if exists).
                close();
                open(((DotClass) stm).getName());
            }

            // Print statement to stream.
            println(stm);
        }
        close();
    }
}
