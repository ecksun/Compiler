lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
javac_flags=-cp $(java_cup_jar):.:../lex

JAVAC=javac
CUP=java -jar $(java_cup_jar)

.PHONY: all clean

all: sym.java parser.class Tree.class

parser.java: parser.cup
	$(CUP) < $^

sym.java: parser.cup
	$(CUP) < $^

%.class: %.java
	$(JAVAC) $(javac_flags) $<

clean:
	-rm -f parser.java sym.java parser.class
