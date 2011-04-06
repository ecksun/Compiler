java_cup_jar=/usr/share/java/cup.jar
javac_flags=-cp ${java_cup_jar}

.PHONY: clean

all: parser.java sym.java parser.class

parser.java: parser.cup
	cup < $^

sym.java: parser.cup
	cup < $^

%.class: %.java
	javac ${javac_flags} $<

clean:
	rm -f parser.java sym.java parser.class
