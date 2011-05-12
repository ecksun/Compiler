class TestNot {
    public static void main(String[] args) {
        System.out.println(new TestNotHelper().run());
    }
}

class TestNotHelper {

    public int run() {
        if (this.testTrue()) {
            System.out.println(1337);
        } else {
            System.out.println(1);
        }

        if (this.testFalse()) {
            System.out.println(1);
        } else {
            System.out.println(1337);
        }

        return 1337;
    }

    public boolean testTrue() {
        boolean x;
        boolean y;
        x = false;
        y = !x;
        return y;
    }

    public boolean testFalse() {
        boolean x;
        boolean y;
        x = true;
        y = !x;
        return y;
    }

}
