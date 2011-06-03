package mjc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            System.out.println("Usage : java JVMMain <inputfile> [-S]");
            System.exit(1);
        }

        boolean assemble = argv.length >= 2 && argv[1].equals("-S");

        parser p = new parser(scanner);
        Program result = null;
        try {
            result = (Program) p.parse().value;
        } catch (Exception e) {
            System.err.println("Exception caught during parsing.");
            System.exit(1);
        }
        System.out.println(result.toString());

        Visitor<?> visitor = new SyntaxTreePrinter(System.out);
        System.out.println(visitor.getClass().getName());
        result.accept(visitor);

        visitor = new SymbolTableVisitor();
        System.out.println(visitor.getClass().getName());
        result.accept(visitor);

        if (((ErrorCollector) visitor).hasErrors()) {
            System.exit(1);
        }

        visitor = new TypeVisitor();
        System.out.println(visitor.getClass().getName());
        result.accept(visitor);

        if (((ErrorCollector) visitor).hasErrors()) {
            System.exit(1);
        }

        visitor = new CodeGeneratorVisitor();
        System.out.println(visitor.getClass().getName());
        result.accept(visitor);

        // Assemble the code to generate executables if -S was given.
        if (assemble) {
            // Derive names of all Jasmin files from program syntax tree.
            List<String> jasminFileNames = new LinkedList<String>();
            jasminFileNames.add(result.main.className.name + JASMIN_EXT);
            for (ClassDecl classDecl : result.classDecls) {
                jasminFileNames.add(classDecl.className.name + JASMIN_EXT);
            }

            // Copy the Jasmin lib to a place with known absolute path.
            File tempDir = new File(System.getProperty("java.io.tmpdir"));
            File jasminJarFile = new File(tempDir, "jasmin.jar");
            InputStream jasminIn = JVMMain.class
                    .getResourceAsStream("/resources/jasmin.jar");
            OutputStream out = null;
            try {
                out = new FileOutputStream(jasminJarFile);
            } catch (FileNotFoundException e) {
                System.err.println("Invalid jasmin jar output file.");
            }

            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = jasminIn.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } catch (IOException e) {
                System.exit(1);
            }

            // Prepare and execute the Jasmin command.
            List<String> command = new LinkedList<String>();
            command.add("java");
            command.add("-jar");
            command.add(jasminJarFile.getAbsolutePath());
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
