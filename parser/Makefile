CUP=java -jar java-cup-11a.jar
java_cup_jar=java-cup-11a.jar
javac_flags=-cp ${java_cup_jar}

.PHONY: all clean

all: parser.java sym.java parser.class

parser.java: parser.cup
	$(CUP) < $^

sym.java: parser.cup
	$(CUP) < $^

%.class: %.java
	javac ${javac_flags} $<

clean:
	rm -f parser.java sym.java parser.class
