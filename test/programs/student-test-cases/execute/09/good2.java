/* Creates an array and quicksorts it. Extensions:
/*      variable declarations allowed in main (Code:VDM)
/*      operators >, >=, <=, ||, ==, !=  
/*      inheritance
/*      for loops              (Code: FOR)
/*      i++ and i-- statements (Code: PLM)
/*      System.out.println() can take int[], boolean, and object type (Code: PRT)
*/
//EXT:ISC
//EXT:ICG
//EXT:JVM
//EXT:IWE
//EXT:CLE
//EXT:CGT
//EXT:CGE
//EXT:CEQ
//EXT:CNE
//EXT:BDJ
//EXT:PLM
//EXT:FOR
//EXT:VDM
//EXT:PRT

class Good2 {

    public static void main(String[] args){
         
        int[] a;
        int b;
        Good2Sorter s;
        int waste;
        
        a=new int[5];
        a[0]=7;
        a[1]=2;
        a[2]=1;
        a[3]=3;
        a[4]=10;
        
        s = new Good2Sorter();
        System.out.println(s);
        System.out.println(a);
        waste=s.quickSort(a,0,a.length-1);
        System.out.println(a);
        System.out.println(a[3]==a[4]);
        System.out.println(a[2]!=a[1]);
        
        for (b = a.length -1; b >= 0; b--)
        System.out.println((a[b]==3) || (b==2));
    }
}

class Good2Bucket {
    int ref;
    int lb;
    int hb;
    int tmp;
}

class Good2Sorter extends Good2Bucket {

    public int quickSort(int[] array, int start, int end){
        int ret;
        int dump;
        
        ret = 1;
        if(start >= end){
            ret = 0;
        }
        if (ret != 0) {
            ref=array[start];
            lb=start;
            hb=end+1;
            while(hb > lb+1){
                if(array[lb+1] <= ref - 1){
                    lb++;
                } else {
                    hb--;
                    tmp=array[hb];
                    array[hb]=array[lb+1];
                    array[lb+1]=tmp;
                }
                array[start]=array[lb];
                array[lb]=ref;
                
                dump = this.quickSort(array,start,lb-1);
                dump = this.quickSort(array,lb+1,end);
            }
        }
        return ret;
    }
}