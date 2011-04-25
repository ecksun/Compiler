class TestPrimeSieve {
    public static void main(String[] argv) {
        System.out.println((new MiniJavaIsAPain()).miniJavaIsStupid());
    }
}

class MiniJavaIsAPain  {
    int miniJavaIsStupid() {
        PrimeSieve ps = new PrimeSieve(); 
        ps.run();
        int i = 0;
        while (i < ps.getSize()) {
            if (ps.isPrime(i)) {
                System.out.println(i);
            }
            i = i + 1;
        }
        return -1;
    }
}

class PrimeSieve {
    int is_prime[];
    int size;
    int run() {
        this.size = 10000;
        is_prime = new int[size]; // Based on that java sets all ints to 0
        is_prime[0] = 1;
        is_prime[1] = 1;
        sieve();
        return 1;
    }

    boolean sieve() {
        int i = 2;
        while (i < size) {
            if (is_prime[i] == 0) {
                int j = i * 2;
                while (j < size) {
                    is_prime[j] = 1;
                    j = j + i;
                }
            }
            i = i + 1;
        }
        return true;
    }

    boolean isPrime(int i) {
        return is_prime[i] == 0;
    }

    int getSize() {
        return size;
    }
}

