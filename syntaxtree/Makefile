lib_dir=../lib
java_cup_jar=$(lib_dir)/java-cup-11a.jar

JAVAC=javac
JAVAC_FLAGS=-cp .:../lex:$(java_cup_jar)

sources=\
		And.java\
		ArrayAssign.java\
		ArrayLength.java\
		ArrayLookup.java\
		Assign.java\
		Block.java\
		BooleanType.java\
		Call.java\
		ClassDecl.java\
		ClassDeclExtends.java\
		ClassDeclList.java\
		ClassDeclSimple.java\
		Exp.java\
		ExpList.java\
		False.java\
		Formal.java\
		FormalList.java\
		Identifier.java\
		IdentifierExp.java\
		IdentifierType.java\
		If.java\
		IntArrayType.java\
		IntegerLiteral.java\
		IntegerType.java\
		LessThan.java\
		MainClass.java\
		Makefile\
		MethodDecl.java\
		MethodDeclList.java\
		Minus.java\
		NewArray.java\
		NewObject.java\
		Not.java\
		Plus.java\
		Print.java\
		Program.java\
		Statement.java\
		StatementList.java\
		SyntaxTreePrinter.java\
		This.java\
		Times.java\
		True.java\
		Type.java\
		VarDecl.java\
		VarDeclList.java\
		While.java
classes=$(sources:.java=.class)

.PHONY: all clean

all: $(sources)
	$(JAVAC) $(JAVAC_FLAGS) $^

clean:
	-rm -f *.class

