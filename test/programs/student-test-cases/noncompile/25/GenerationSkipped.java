//EXT:ISC

class GenerationSkipped {
//this tests method signature across a "skipped" generation 
	public static void main ( String [ ] id ) 
	{ 
		int r;
		TestClass c;
		c = new TestClass2();
		r = c.createSelf().helloClassWorld(3,9);
		System.out.println(r);
		System.out.println(new TestClass().createSelf().helloClassWorld(3,9));
	} 
	
}


class Test1 {
	public int helloClassWorld(int k, int g) {
		int r;
		r = 1;
		return k+r+g;
	}
	
	public TestClass createSelf() {
		return new TestClass();
	} 
}


class TestClass extends Test1{
	
	
}

class TestClass2 extends TestClass {

	public int helloClassWorld(int k, int g, int h) {
		int r;
		r = 2;
		return k+g;
	}
	
	public TestClass2 createSelf() {
		return this;
	} 

}




