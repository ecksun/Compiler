class MainClass {
	public static void main(String[] args) {
		System.out.println( new A().doStuff() );
	}
}

class booleanarray {
	int[] array;
	
	public boolean create(int len)
	{
		array = new int[len];
		return true;
	}
	public int getLength()
	{
		return array.length;
	}
	public boolean set(int pos, boolean value)
	{
		boolean success;
		if( 0 < pos+1 && pos < this.getLength() )
		{
			if(value)
				array[pos] = 1;	// 1 is true
			else
				array[pos] = 0;	// 0 is false
			
			success = true;
		} else
			success = false;
		
		return success;
	}
	public boolean get(int pos)
	{
		boolean retval;
		if(array[pos] < 1)	// Then it is zero
			retval = false;
		else
			retval = true;
		
		return retval;			
	}
	public int[] getArray()
	{
		return array;
	}
}

class A {
	public boolean doStuff()
	{
		// Variable declarations
		int i;
		booleanarray ba;
		boolean start;
		
		// Assigning stuff
		i = 0;
		ba = new booleanarray();
		start = true;
		
		System.out.println(ba.create(10));
		
		while(i < ba.getLength() && ba.set(i, start)) 
		{
			start = !start;
			i = i + 1;
		}
		
		// Should be true
		System.out.println(ba.get(2));
		// Will become false
		if( ba.set(2, false) )
			System.out.println(ba.get(2));			// Should be false
		else
			System.out.println(43110);				// Should not happen
		
		return ba.get(8);
	}
}

