class Test4 {

    public static void main(String[] args) {
        System.out.println((new Test4A()).test());
    }

}

class Test4A {

    Test4B test4B;

    public int test() {
        int test1;
        int test2;
        test1 = this.test1(100000, 200000, 300000);
        test2 = 999999;

        test4B = new Test4B();
        System.out.println(test4B.test());

        return test1;
    }

    public int test1(int a, int b, int c) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        return 1;
    }

    public int test1(int a, int b) {
        System.out.println(a);
        System.out.println(b);
        return 2;
    }

}

class Test4B {

    public int test() {
        return 1337;
    }

}
