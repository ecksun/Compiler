lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
javac_flags=-cp $(java_cup_jar):.

JFLEX=$(lib_dir)/jflex/bin/jflex
JAVAC=javac

.PHONY: all clean

all: SymbolName.class Lexer.java Lexer.class

clean:
	-rm -f *.class Lexer.java

Lexer.java: minijava.lex
	$(JFLEX) $<

%.class: %.java
	$(JAVAC) $(javac_flags) $<

