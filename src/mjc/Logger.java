package mjc;

public class Logger {

    /**
     * Whether or not this Logger should make any noise.
     */
    public static boolean verbose;

    public static void println(boolean x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(char x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(char[] x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(double x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(float x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(int x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(long x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(Object x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void println(String x) {
        if (verbose) {
            System.out.println(x);
        }
    }

    public static void print(String x) {
        if (verbose) {
            System.out.println(x);
        }
    }

}
