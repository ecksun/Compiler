class TestPrimeSieve {
    public static void main(String[] argv) {
        System.out.println((new MiniJavaIsAPain()).miniJavaIsStupid());
    }
}

class MiniJavaIsAPain  {
    MiniJavaIsAPain() {
        PrimeSieve ps = new PrimeSieve(); 
        for (int i = 0; i < ps.getSize(); ++i) {
            if (ps.isPrime(i)) {
                System.out.println(i);
            }
        }
    }
    int miniJavaIsStupid() {
        return -1;
    }
}

class PrimeSieve {
    int is_prime[];
    int size;
    PrimeSieve() {
        this.size = 10000;
        is_prime = new int[size]; // Based on that java sets all ints to 0
        is_prime[0] = 1;
        is_prime[1] = 1;
        sieve();
    }

    boolean sieve() {
        for (int i = 2; i < size; ++i) {
            if (is_prime[i] == 0) {
                for (int j = i*2; j < size; j = j + i) {
                    is_prime[j] = 1;
                }
            }
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

