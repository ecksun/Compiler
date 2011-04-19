lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
classpath=.:..:$(java_cup_jar)

JAVAC=javac
JAVAC_FLAGS=-cp $(classpath)
CUP=java -jar $(java_cup_jar)

.PHONY: all clean

all: sym.java parser.java parser.class

parser.java: parser.cup
	$(CUP) < $^

sym.java: parser.cup
	$(CUP) < $^

%.class: %.java
	$(JAVAC) $(JAVAC_FLAGS) $<

clean:
	-rm -f parser.java sym.java *.class
