##################################################
## General configuration
## =====================

# Every Makefile should contain this line:
SHELL = /bin/sh

# Runtime program for executing compiled Java programs.
JAVA = java
# Extra flags to pass to the Java runtime program.
JAVAFLAGS = 
# All flags to pass to the Java runtime program.
java_all_flags = $(JAVAFLAGS)\
				 -classpath $(bin_dir):$(jcup_jar)

# Program for compiling Java programs. 
JAVAC = javac
# Extra flags to give to the Java compiler (could be set when calling `make`).
JAVACFLAGS =
# All flags to pass to the Java compiler.
javac_all_flags = $(JAVACFLAGS)\
				  -classpath $(jcup_jar):${jflex_jar}\
				  -d $(bin_dir)\
				  -sourcepath src:$(lex_dir):$(parser_dir):$(syntaxtree_dir)


# JFlex binary to use for lexer generation.
JFLEX = java -jar $(jflex_jar)

# Java Cup command (relative to parser dir) to use for parser generation.
JCUP = java -jar $(jcup_jar)

# Search path for all prerequisities, i.e. where `make` should look for files.
VPATH = $(lex_dir):$(parser_dir)


##################################################
## Setup files and directories variables
## =====================================

# Sub-directories within this project.
bin_dir = bin
lex_dir = src/lex
lib_dir = lib
parser_dir = src/parser
syntaxtree_dir = src/syntaxtree
test_dir = test

# Java Cup and ohter lib JAR file.
jcup_jar = $(lib_dir)/java-cup-11a.jar
jflex_jar = $(lib_dir)/JFlex.jar

# Java source files.
java_sources = src/**/*.java

# Default test input file to pass to test main program.
TEST_FILE = $(test_dir)/programs/execute/TestPrimeSieve.java


##################################################
## Targets
## ================

.PHONY: all clean classes

all: lex parser classes

run_test: all
	$(JAVA) $(java_all_flags) mjc.JVMMain $(TEST_FILE)

classes: $(bin_dir)
	$(JAVAC) $(javac_all_flags) $(java_sources)

$(bin_dir):
	-mkdir -p $@

clean: clean_lex clean_parser
	-rm -rf $(bin_dir)/*

# Include lexer targets.
include $(lex_dir)/lex.mk

# Include parser targets.
include ${parser_dir}/parser.mk


##################################################
## Notes
## =====

# $@ expands to the file name of the target
#
# Phony targets are targets whose names are not files that will be created by
# their recipe. Specifying a target as phony serves two reasons: avoiding file
# and target name conflicts, and to improve performance.
# -- http://www.gnu.org/software/make/manual/make.html#Phony-Targets
