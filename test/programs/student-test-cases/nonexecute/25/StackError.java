

class StackError{
	public static void main(String[] args) {
		Field f;
		int r;
		f = new Field();
		
		r = f.init();
		r = f.hej(2);
	}
    
}

class Field {

	int i;
	Field f;
	int[] a;
	
	public int getI() {
		return i;
	}
	
	public int setI(int v) {
		i = v;
		return 0;
	}
	
	public int init() {
		f = new Field();
		i = f.init();
		a = new int[10];
		return 0;
	}
	public int hej(int j) {
		j = f.setI(101);
		j = f.getI();
		return j;
	}
}
