class Test4 {

    public static void main(String[] args) {
        System.out.println((new Test4A()).test());
    }

}


class Test4A {

    public int test() {
        int test1;
        test1 = this.test1(1, 2, 3);
        return test1;
    }

    public int test1(int a, int b, int c) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        return 0;
    }

}
