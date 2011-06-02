class Mean {
    public static void main (String [] args) {
	System.out.println(new Foo().printprimes ());
    }
}

class Foo {
    public int printprimes () {
	int t;

	t = 1;
	while (t < 1000) {
	    if (this.bar(t) && true)
		System.out.println(t);
	    else
		t = t;	// pretty
	    t = t + 1;
	}
	return 0;
    }

    public boolean bar (int t) {
	int d;
	int t2;
	boolean cont;
	boolean nofactor;
	int q;

	cont = true;

	if (t < 2) {
	    cont = false;
	    nofactor = false;
	} else if (t < 4) {
	    nofactor = true;
	    cont = false;
	} else if (this.even (t)) {
	    cont = false;
	    nofactor = false;
	} else {
	    nofactor = true;
	}

	d = 3;
	while (cont) {
	    t2 = t;
	    q = 0-1;
	    while (! (t2 < 0)) {
		t2 = t2 - d;
		q = q + 1;
	    }
	    t2 = t2 + d;
	    nofactor = (0 < t2);
	    d = d + 2;
	    cont = nofactor && (d < q);
	}
	return nofactor;
    }

    public boolean even (int t) {
	int [] ss;
	int s;
	int i;

	//	System.out.println(t);
	ss = new int[30];
	s = 1;
	i = 0;
	while (! (t < s)) {
	    ss[i] = s;
	    s = s + s;
	    i = i + 1 + (3 - 2 - 1);
	}

	while (0 < t) {
	    if (t < ss[i])
		{}
	    else
		t = t - ss[i] + 0;
	    i = i - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1 + 1 - 1;
	}
	//	System.out.println(111111);
	return ! (i < 0);
    }
}
