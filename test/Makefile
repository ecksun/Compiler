lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar
java_cp=..:$(java_cup_jar)

JAVA=java
JAVA_FLAGS=-cp $(java_cp)

JAVAC=javac
JAVAC_FLAGS=-cp $(java_cp)


.PHONY: all run clean

all: Main.class

run: Main.class
	$(JAVA) $(JAVA_FLAGS) Main Test.java

clean:
	-rm -f *.class

%.class: %.java
	$(JAVAC) $(JAVAC_FLAGS) $<
