class Test4{
    public static void main(String[] a){
	System.out.println(new Duck().isHappy());
    }
}


class Duck{
	
	boolean isSad;
	
	public boolean isHappy() {
		
		return !isSad;
	}

}

