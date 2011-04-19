/**
 * This should pass grammar parse phase, but fail later.
 */
class Test01 {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(this);
        } else {
            System.out.println(args[0]);
        }   
    }

}
