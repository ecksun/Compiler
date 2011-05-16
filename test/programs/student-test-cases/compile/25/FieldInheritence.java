//EXT:ISC
//EXT:JVM

class FieldInheritence{
	public static void main(String[] args) {
		Field2 f;
		int r;
		f = new Field2();
		
		r =f.setI(101);
		r = f.getI();
	}
    
}

class Field2 extends Field {
	public int getI() {
		return i;
	}

}

class Field {

	int i;
	Field f;
	int[] a;
	
	
	public int setI(int v) {
		i = v;
		return 0;
	}
	
}
