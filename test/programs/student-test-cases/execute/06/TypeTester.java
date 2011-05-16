// EXT:NBD
class TypeTester{
	public static void main(String[] args){
		{
			Lolwut q;
			q = new Lolwut();
		}
		System.out.println(1);
	}
}

class Lolwut{
	int roflcon;
	int asdf;
	int randomValue;
	int foo;
	
	public int setup(int dummy){
		int dd;
		int randomValue;
		dd = 4*6+9*2-this.getRandom(12) + new Mirror().mirror(7);
		if(dummy > dd){
			int randomValue;
			randomValue = 7;
			dd = randomValue;
		}else
			dd = 5;
		return dd;
	}
	
	public int getRandom(int dummy){
		return randomValue;
	}
}

class IntWrap{
	int val;
}

class Mirror{
	public int mirror(int val){
		return val;
	}
	
	public boolean mirror(boolean val){
		return val;
	}
	
	public int[] mirror(int[] val){
		return val;
	}
	
	public Mirror mirror(Mirror val){
		return val;
	}
	
	public IntWrap mirror(IntWrap val){
		return val;
	}
}