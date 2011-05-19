.PHONY: lex
lex: Lexer.java

Lexer.java: minijava.lex
	$(JFLEX) $<

.PHONY: clean_lex
clean_lex:
	-rm -f $(addprefix $(lex_dir)/, Lexer.java Lexer.java~)
