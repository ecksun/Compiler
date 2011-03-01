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
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
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
    "public"            { return symbol(SymbolName.PUBLIC); }
    "class"             { return symbol(SymbolName.CLASS); }
    "return"            { return symbol(SymbolName.RETURN); }
    "length"            { return symbol(SymbolName.LENGTH); }
    "this"              { return symbol(SymbolName.THIS); }
    "new"               { return symbol(SymbolName.NEW); }


    "void"              { return symbol(SymbolName.VOID); }
    "boolean"           { return symbol(SymbolName.BOOLEAN); }
    "true"              { return symbol(SymbolName.FALSE); }
    "false"             { return symbol(SymbolName.TRUE); }
    "int"               { return symbol(SymbolName.INT); }
    "String"            { return symbol(SymbolName.STRING); }

    "if"                { return symbol(SymbolName.IF); }
    "else"              { return symbol(SymbolName.ELSE); }
    "while"             { return symbol(SymbolName.WHILE); }

    "System.out.println"    { return symbol(SymbolName.PRINT); }

    "{"                 { return symbol(SymbolName.LBRACE); }
    "}"                 { return symbol(SymbolName.RBRACE); }
    "("                 { return symbol(SymbolName.LPAREN); }
    ")"                 { return symbol(SymbolName.RPAREN); }
    "["                 { return symbol(SymbolName.LBRACK); }
    "]"                 { return symbol(SymbolName.RBRACK); }
    ";"                 { return symbol(SymbolName.SEMICOLON); }
    ","                 { return symbol(SymbolName.COMMA); }
    "."                 { return symbol(SymbolName.DOT); } 
    "="                 { return symbol(SymbolName.EQ); } 
    "!"                 { return symbol(SymbolName.NOT); } 

    "&&"                { return symbol(SymbolName.ANDAND); }
    "<"                 { return symbol(SymbolName.LT); }
    "+"                 { return symbol(SymbolName.PLUS); }
    "-"                 { return symbol(SymbolName.MINUS); }
    "*"                 { return symbol(SymbolName.MULT); }

    {WhiteSpace}        { }
    
    {Comment}           { }
}

