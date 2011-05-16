// EXT:ISC 

class MainClass{
    public static void main(String[] args){
    	System.out.println(1);
    }
}

class A {
	public int method() {return 1;}
}

class B extends A {
	public boolean method() {return true;}	//error
}
