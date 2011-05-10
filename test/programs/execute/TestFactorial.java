class TestFactorial {
    public static void main(String[] argv) {
        System.out.println((new MiniJavaIsAPain()).recFac(10));
        System.out.println((new MiniJavaIsAPain()).itFac(10));
    }
}

class MiniJavaIsAPain {
    public int recFac(int n) {
        int ret;
        ret = 1;
        if (0 < n && n < 2) {
            ret = 1;
        }
        else {
            ret = n * this.recFac(n-1);
        }
        return ret;
    }
    public int itFac(int n) {
        int res;
        int i;
        res = 1;
        i = 1;
        while (i < n+1) {
            res = res * i;
            i = i + 1;
        }
        return res;
    }
}

