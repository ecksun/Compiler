import java_cup.runtime.*;
import java_cup.*;

%%

%class Lexer
%unicode
%line
%column
%cup
%int

%{
    StringBuilder string = new StringBuilder();

    private JavaSymbol symbol(int type) {
        return new JavaSymbol(type, yyline, yycolumn);
    }
    private JavaSymbol symbol(int type, Object value) {
        return new JavaSymbol(type, yyline, yycolumn, value);
    }

%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

%state STRING

%%

<YYINITIAL> {
    "public"            { return symbol(sym.PUBLIC); }
    "class"             { return symbol(sym.CLASS); }
    "return"            { return symbol(sym.RETURN); }
    "length"            { return symbol(sym.LENGTH); }
    "this"              { return symbol(sym.THIS); }
    "new"               { return symbol(sym.NEW); }


    "void"              { return symbol(sym.VOID); }
    "boolean"           { return symbol(sym.BOOLEAN); }
    "true"              { return symbol(sym.FALSE); }
    "false"             { return symbol(sym.TRUE); }
    "int"               { return symbol(sym.INT); }
    "String"            { return symbol(sym.STRING); }

    "if"                { return symbol(sym.IF); }
    "else"              { return symbol(sym.ELSE); }
    "while"             { return symbol(sym.WHILE); }

    "System.out.println"    { return symbol(sym.PRINT); }

    "{"                 { return symbol(sym.LBRACE); }
    "}"                 { return symbol(sym.RBRACE); }
    "("                 { return symbol(sym.LPAREN); }
    ")"                 { return symbol(sym.RPAREN); }
    "["                 { return symbol(sym.LBRACK); }
    "]"                 { return symbol(sym.RBRACK); }
    ";"                 { return symbol(sym.SEMICOLON); }
    ","                 { return symbol(sym.COMMA); }
    "."                 { return symbol(sym.DOT); } 
    "="                 { return symbol(sym.EQ); } 
    "!"                 { return symbol(sym.NOT); } 

    "&&"                { return symbol(sym.ANDAND); }
    "<"                 { return symbol(sym.LT); }
    "+"                 { return symbol(sym.PLUS); }
    "-"                 { return symbol(sym.MINUS); }
    "*"                 { return symbol(sym.MULT); }

    {WhiteSpace}        { }
    
    {Comment}           { }
}

