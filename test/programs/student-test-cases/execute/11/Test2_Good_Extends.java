class Test2{
    public static void main(String[] a){
	System.out.println(new Volvo().init());
    }
}


class Car extends Object{

	int color;
	int speed;
	int price;

	public int lolinit() {
		color = 5; 	
		speed = 5; 	
		price = 5;	
		return speed + price + color;
	}	
}

class Volvo extends Car {

	int safetybelts;
	Car c;
	int i;
	
	public int init() {
		
		i = c.lolinit();
		safetybelts = 5 + i;
				
		return safetybelts;
	}
}

class Object{

	int size;
	public int init() {
		size = 4;
		return size;
	}

}

// EXT:ISC
// EXT:ICG
