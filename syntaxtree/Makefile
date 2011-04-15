lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
javac_flags=-cp .:../lex:$(java_cup_jar)

JAVAC=javac
CUP=java -jar $(java_cup_jar)

.PHONY: all clean

all: *.class

%.class: %.java
	$(JAVAC) $(javac_flags) $<

clean:
	-rm -f *.class
