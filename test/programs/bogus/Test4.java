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

    int x;
    boolean y;
    int[] z;

    public Test4B getTest4B() {
        // x has already been declared in this scope.
        boolean x;
        // y has already been declared in this scope.
        int[] y;
        // z has already been declared in this scope.
        int z;

        return new Test4B();
    }

}

class Test4B {
}
