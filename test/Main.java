package test;

import lex.Lexer;
import parser.parser;
import syntaxtree.Program;
import syntaxtree.visitor.SymbolTableVisitor;
import syntaxtree.visitor.SyntaxTreePrinter;
import syntaxtree.visitor.Visitor;

public class Main {

    public static void main(String argv[]) 
        throws java.io.IOException, java.lang.Exception
    {
        Lexer scanner = null;
        try {
            scanner = new Lexer( new java.io.FileReader(argv[0]) );
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \""+argv[0]+"\"");
            System.exit(1);
        }
        catch (java.io.IOException e) {
            System.out.println("Error opening file \""+argv[0]+"\"");
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage : java Main <inputfile>");
            System.exit(1);
        }

        try {
            parser p = new parser(scanner);
            Object result = p.debug_parse().value;
            System.out.println(result.toString());
            
            Visitor visitor = new SyntaxTreePrinter(System.out);
            ((Program)result).accept(visitor);
            
            System.out.println("TypeCheckVisitor:");
            visitor = new SymbolTableVisitor();
            ((Program)result).accept(visitor);
            
//            IdentificationVisitor identification = new IdentificationVisitor();
//            ((Prog)result).accept( identification );
//            System.out.println("Code:\n");
//            EmitCodeVisitor emitcode = new EmitCodeVisitor();
//            //     ((Prog)result).accept( emitcode );
//            ((Prog)result).traverseBottomUp( emitcode );
        }
        catch (java.io.IOException e) {
            System.out.println("An I/O error occured while parsing : \n"+e);
            System.exit(1);
        }
    }
}




