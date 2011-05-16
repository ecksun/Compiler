  
class UninitializedLocal {
    public static void main(String[] args) {
        System.out.println(new Test().method1());
        System.out.println(new Test().method2());
    }
}

class Test {
    public int method1() {
        int i;
        return i;
    }
    
    public int method2() {
        int i;
        System.out.println(i);
        return 0;
    }
}
