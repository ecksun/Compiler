package lex;

import java_cup.runtime.Symbol;
import parser.sym;

%%

%class Lexer
%public
%unicode
%line
%column
%cup
%int
%yylexthrow lex.NoTokenMatchException

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t]*

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

// XXX: Is "4711B" really the tokens "4711" and "B"?
Identifier = [:jletter:] [:jletterdigit:]*

Number = 0 | [1-9][0-9]*

%%

<YYINITIAL> {
    "public"            { return symbol(sym.PUBLIC); }
    "class"             { return symbol(sym.CLASS); }
    "static"            { return symbol(sym.STATIC); }
    "return"            { return symbol(sym.RETURN); }
    "length"            { return symbol(sym.LENGTH); }
    "this"              { return symbol(sym.THIS); }
    "new"               { return symbol(sym.NEW); }
    "main"              { return symbol(sym.MAIN); }


    "void"              { return symbol(sym.VOID); }
    "boolean"           { return symbol(sym.BOOLEAN); }
    "true"              { return symbol(sym.TRUE); }
    "false"             { return symbol(sym.FALSE); }
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

    {Identifier}        { return symbol(sym.IDENTIFIER, yytext()); }

    {Number}           { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }

    {WhiteSpace}        { }
    
    {Comment}           { }

    /* error fallback */
	.                   { throw new NoTokenMatchException(yytext(), yyline+1, yycolumn+1); }

}

