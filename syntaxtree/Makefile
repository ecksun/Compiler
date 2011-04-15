lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar

JAVAC=javac
JAVAC_FLAGS=-cp .:visitor:../lex:$(java_cup_jar)

.PHONY: classes clean

classes: *.java visitor/*.java
	$(JAVAC) $(JAVAC_FLAGS) $^

clean:
	-rm -f *.class

