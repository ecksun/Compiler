# The full path to the the generated parser files.
parser_files = $(addprefix $(parser_dir)/, parser.java sym.java)

.PHONY: parser
parser: parser.java sym.java

# Generate the target files normally, then move them into the correct dir.
parser.java: parser.cup
	$(JCUP) < $<
	mv $@ sym.java $(parser_dir)

# A bit ugly; sym.java will only be built as long as parser.java does not exist.
sym.java: parser.java


.PHONY: clean_parser
clean_parser:
	-rm -f $(parser_files)
