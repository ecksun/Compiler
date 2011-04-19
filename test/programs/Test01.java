/**
 * This should pass grammar parse phase, but fail later.
 */
class TestA {

    public static void main(String[] args) {

        if (args.length == 1) {
            System.out.println(args[0]);
        } else {
            System.out.println(this);
        }   
    }

}
