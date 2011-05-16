
class FibPrint{
	public static void main(String[] args) {
		Fib f;
		int r;
		f = new Fib();
		r = 1;
		r = f.printFib(1000);
	}
    
}


class Fib {
	int current;
	int prev;
	
	public int printFib(int range) {
		int i;
		
		prev = 0;
		current =1;
		i = 0;
		while( i < range ) {
			System.out.println( this.next() );
			i = i +1;
		}
		return 0;
	}
	
	public int next() {
		int v;
		v = prev + current;
		prev = current;
		current = v;
		return v;
	}
}
