/**
 * Minus operation tests, stolen directly from email from tg.
 *
 * The creator of these test cases is thus, Torbjörn Granlund, but they were
 * wrapped inside a MiniJava class by us.
 */
class TestMinus {
    public static void main(String[] args) {
        if (new TestMinusHelper().testMinus()) {} else {}
    }
}

class TestMinusHelper {
    public boolean testMinus() {
        int x;

        // NOTE: All integers operations are well-defined mod 2^32.
        x = this.print(2-1); // subtract the constant 1
        x = this.print(2+-1); // add the contant -1
        x = this.print(2--1); // subtract the constant -1
        x = this.print(2-2147483647); // subtract largest positive int
        x = this.print(2+-2147483648); // add largest negative int
        x = this.print(2--2147483648); // subtract largest negative int

        // Correct Java for reference output.
        //x = print(2-1); // subtract the constant 1
        //x = print(2+(-1)); // add the contant -1
        //x = print(2-(-1)); // subtract the constant -1
        //x = print(2-2147483647); // subtract largest positive int
        //x = print(2+(-2147483648)); // add largest negative int
        //x = print(2-(-2147483648)); // subtract largest negative int

        return true;
    }

    public int print(int x) {
        System.out.println(x);
        return x;
    }
}
