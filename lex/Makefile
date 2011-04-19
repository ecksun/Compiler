lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
javac_flags=-cp $(java_cup_jar):.:..

JFLEX=$(lib_dir)/jflex/bin/jflex
JAVAC=javac

.PHONY: all clean classes

all: Lexer.java classes

classes: *.java
	$(JAVAC) $(javac_flags) $^

Lexer.java: minijava.lex
	$(JFLEX) $<

clean:
	-rm -f *.class Lexer.java
