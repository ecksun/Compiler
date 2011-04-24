package lex;

/**
 * Thrown when a lexer can not match input with any valid token.
 * 
 * @author Joel Pettersson
 * @author Linus Wallgren
 */
public class NoTokenMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String input;
	private int row, column;

	/**
	 * Constructs a new {@link NoTokenMatchException}.
	 * 
	 * @param input
	 *            The string that the lexer could not match with a token.
	 * @param row
	 *            The row where the unmatched input string was found.
	 * @param column
	 *            The column where the unmatched input string was found.
	 */
	public NoTokenMatchException(String input, int row, int column) {
		this.input = input;
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns a short description of the exception.
	 */
	public String toString() {
		return String.format("Illegal character at row %d and column %d: %s",
				row, column, input);
	}
}
