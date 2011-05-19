package mjc;

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

    private final static String JASMIN_EXT = ".j";
    
    public static void main(String argv[]) throws java.io.IOException,
            java.lang.Exception {
        Lexer scanner = null;
        try {
            scanner = new Lexer(new java.io.FileReader(argv[0]));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \"" + argv[0] + "\"");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage : java JVMMain <inputfile>");
            System.exit(1);
        }

        try {
            parser p = new parser(scanner);
            Program result = (Program) p.debug_parse().value;
            System.out.println(result.toString());

            Visitor<?> visitor = new SyntaxTreePrinter(System.out);
            System.out.println(visitor.getClass().getName());
            result.accept(visitor);

            visitor = new SymbolTableVisitor();
            System.out.println(visitor.getClass().getName());
            result.accept(visitor);
            
            if (((ErrorCollector) visitor).hasErrors())
                System.exit(1);

            visitor = new TypeVisitor();
            System.out.println(visitor.getClass().getName());
            result.accept(visitor);
            
            if (((ErrorCollector) visitor).hasErrors())
                System.exit(1);
            
            visitor = new CodeGeneratorVisitor();
            System.out.println(visitor.getClass().getName());
            result.accept(visitor);

            // Assemble the code to generate executables.
            List<String> command = new LinkedList<String>();
            command.add("java");
            command.add("-jar");
            command.add("lib/jasmin.jar");
            command.add(result.main.className.name + JASMIN_EXT);
            for (ClassDecl classDecl : result.classDecls) {
                command.add(classDecl.className.name + JASMIN_EXT);
            }
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.start();
            
        } catch (java.io.IOException e) {
            System.out.println("An I/O error occured while parsing : \n" + e);
            System.exit(1);
        }
    }
}
