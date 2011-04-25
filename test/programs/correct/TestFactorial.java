class TestFactorial {
    public static void main(String[] argv) {
        System.out.println((new MiniJavaIsAPain()).recFac(10));
        System.out.println((new MiniJavaIsAPain()).itFac(10));
    }
}

class MiniJavaIsAPain  {
    int recFac(int n) {
        int ret = 1;
        if (n == 1) {
            ret = 1;
        }
        else {
            ret = n * recFac(n-1);
        }
        return ret;
    }
    int itFac(int n) {
        int res = 1;
        int i = 1;
        while (i < n+1) {
            res = res * i;
            i = i + 1;
        }
        return res;
    }
}

