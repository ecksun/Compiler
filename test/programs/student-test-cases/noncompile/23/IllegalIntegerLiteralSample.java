class IllegalIntegerLiteralSample {
    public static void main(String[] args) {
        /* Single 0: valid. */
        System.out.println(0);
        /*
         * Multiple leading 0: octal constant not accepted. Depending
         * on the how the lexer is built, this is either a lexical
         * error (most "natural" human interpretation) or a syntax
         * error (if the lexer actually produces four consecutive
         * 0 tokens).
         */
        System.out.println(0000);
    }
}
