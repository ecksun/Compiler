package mjc;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import lex.Lexer;
import parser.parser;
import syntaxtree.ClassDecl;
import syntaxtree.Program;
import syntaxtree.visitor.CodeGeneratorVisitor;
import syntaxtree.visitor.ErrorCollector;
import syntaxtree.visitor.SymbolTableVisitor;
import syntaxtree.visitor.SyntaxTreePrinter;
import syntaxtree.visitor.TypeVisitor;
import syntaxtree.visitor.Visitor;

public class JVMMain {

    public final static String JASMIN_EXT = ".j";

    public static void main(String argv[]) {
        Lexer scanner = null;
        try {
            scanner = new Lexer(new java.io.FileReader(argv[0]));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \"" + argv[0] + "\"");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage : java JVMMain <inputfile> [-S] [-v]");
            System.exit(1);
        }

        boolean assemble = !(argv.length >= 2 && argv[1].equals("-S"));
        Logger.verbose = (argv.length >= 2 && argv[1].equals("-v"))
                || (argv.length >= 3 && argv[2].equals("-v"));

        parser p = new parser(scanner);
        Program result = null;
        try {
            result = (Program) (Logger.verbose ? p.debug_parse().value : p
                    .parse().value);
        } catch (Exception e) {
            System.err.println("Exception caught during parsing.");
            System.exit(1);
        }
        Logger.println(result.toString());

        Visitor<?> visitor;
        if (Logger.verbose) {
            visitor = new SyntaxTreePrinter(System.out);
            Logger.println(visitor.getClass().getName());
            result.accept(visitor);
        }

        visitor = new SymbolTableVisitor();
        Logger.println(visitor.getClass().getName());
        result.accept(visitor);

        if (((ErrorCollector) visitor).hasErrors()) {
            System.exit(1);
        }

        visitor = new TypeVisitor();
        Logger.println(visitor.getClass().getName());
        result.accept(visitor);

        if (((ErrorCollector) visitor).hasErrors()) {
            System.exit(1);
        }

        visitor = new CodeGeneratorVisitor();
        Logger.println(visitor.getClass().getName());
        result.accept(visitor);

        // Assemble the code to generate executables if -S was given.
        if (assemble) {
            // Derive names of all Jasmin files from program syntax tree.
            List<String> jasminFileNames = new LinkedList<String>();
            jasminFileNames.add(result.main.className.name + JASMIN_EXT);
            for (ClassDecl classDecl : result.classDecls) {
                jasminFileNames.add(classDecl.className.name + JASMIN_EXT);
            }

            // Prepare and execute the Jasmin command.
            List<String> command = new LinkedList<String>();
            command.add("java");
            command.add("-classpath");
            command.add(System.getProperty("java.class.path"));
            command.add("jasmin.Main");
            for (String fileName : jasminFileNames) {
                command.add(fileName);
            }
            ProcessBuilder pb = new ProcessBuilder(command);
            Process jasminProc;
            try {
                jasminProc = pb.start();
                jasminProc.waitFor();
            } catch (IOException e) {
                System.exit(1);
            } catch (InterruptedException e) {
                System.exit(1);
            }

            // Delete all intermediate Jasmin files.
            for (String fileName : jasminFileNames) {
                new File(fileName).delete();
            }
        }
    }

}
