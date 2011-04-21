/**
 * Bogus test program 4 with duplicate variable declarations.
 */
class Test4 {
    public static void main(String[] id) {
    }
}

class Test4A {

    int a;
    // a has already been declared.
    int a;

    int b;
    // b has already been declared.
    boolean b;
    // b has already been declared.
    int[] b;

    public Test4B getTest4B() {
        int a;
        int b;
        // b has already been declared.
        boolean b;
        // b has already been declared.
        int[] b;

        return new Test4B();
    }

}

class Test4B {
}
