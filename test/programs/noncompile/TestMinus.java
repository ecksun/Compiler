/**
 * Test cases stolen directly from mail from tg about minus operation.
 *
 * The creator of these test cases is thus Torbjörn Granlund, but they were
 * wrapped inside this MiniJava test class by us.
 */
class TestInvalidMinus {
    public static void main(String[] args) {
        if (new TestInvalidMinusHelper().invalidMinus()) {} else {}
    }
}

class TestInvalidMinusHelper {
    public boolean invalidMinus() {
        int x;

        x = 2-2147483648;   // subtract out-of-range number
        x = 2--x;           // subtract unary minus of x, not Minijava
    }
}
