class Heap {
	private int[] heapArray; 
	private int[] hPos;
	private int[] dist;
	int heapSize;
	static int maxH = 100;

	// The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
	public Heap(int maxSize, int[] _dist, int[] _hPos) {
	   heapSize = 0;
	   heapArray = new int[maxH+1];
	   hPos = new int[maxH+1];
	   dist = _dist;
       hPos = _hPos;
	}
	
	public boolean isEmpty() 
    {
        return heapSize == 0;
    }

	public Heap(int size) {
	   heapSize = 0;
	   heapArray = new int[size + 1];
	   hPos = new int[size + 1];
	}

	public void insert( int New_val) {
	   heapArray[++heapSize] = New_val;
	   siftUp( heapSize);
	}

	public void siftUp( int heapSize) {
	   int pos = heapArray[heapSize];
	   heapArray[0] = Integer.MAX_VALUE;

	   while( pos > heapArray[heapSize/2]) {
		  heapArray[heapSize] = heapArray[heapSize/2];
		  heapSize /=2;
	   }
	   heapArray[heapSize] = pos;
	}

	public void siftDown( int parent, int temp) {
		int child;
        int pos;
        pos = heapArray[1];
        child = 2;
		
        while (child <= heapSize)
        {
            if (child < heapSize && heapArray[child] < heapArray[child + 1])
                child++;
            if (temp >= heapArray[child])
                break;
 
            heapArray[parent] = heapArray[child];
            parent = child;
            child *= 2;
        }
        heapArray[parent] = temp;
	}


	public void remove() {
		int temp = heapArray[heapSize--];
		siftDown(1, temp);
    }

	public void display() {
	   System.out.println("\nheapSize\nThe tree structure of the heaps is:");
	   System.out.println( heapArray[1] );
	   for(int i = 1; i<= heapSize/2; i = i * 2) {
		  for(int child = 2*i; child < 4*i && child <= heapSize; ++child)
			 System.out.print( heapArray[child] + "  ");
		   System.out.println();
	   }
	}

	public static void main(String args[]) {
	   int d[] = {0, 100, 70, 120, 20, 60 , 50, 130, 90, 60, 11, 154, 43, 114, 52, 76};
	   int u; 
	   double New_val;
	   int hPos[] = new int[16];
	   Heap h = new Heap(15, d, hPos);

	   for(int i = 1; i <= 10; ++i)  {
		  // pick a heap random value between 1 and 15 and 
          // insert into heap if not already there
		  New_val =  Math.random()*15.0;
		  u = (int) New_val + 1;
            if(hPos[u] == 0) {
                h.insert(u);
			}
	   }
	   
	   h.display();
	   
	   for (int i = 1; i <=10; ++i) {
		   System.out.println("\n Removing next element in the heap ");
		   h.remove();
		   h.display();
	   }
	}

} // end of Heap class




