// Sorted linked list with a sentinel node
import java.util.Scanner; 

class SortedLL
{	
	// internal data structure
    class Node
	{
    	int data;
		Node next;
    }
	
	Node head;
	Node z;
	Node tail;
    
	// constructor code to be added here
    public SortedLL() 
    {
		z = new Node(); //sentinel node(dummy z node) 
		z.next = z;
		head = z;
		tail = null;
    }

	// this is the crucial method
    public void insert (int x)
    {
		Node prev, curr, t;
		t = new Node();
		t.data = x; 
		t.next = z;
		curr = head;
		prev = null;

		//loop to find correct location in list
		while (curr != z && t.data > curr.data)
		{
			prev = curr;
			curr = curr.next;
		}
		//insert new node at beginning of list
		if (prev == null)
		{
			t.next = head;
			head = t;
		}
		//insert new node between prev and curr
		else
		{	
			prev.next = t;
			t.next = curr;
		}
	}	
    
    public boolean remove (int x) 
    {
		boolean removed;
		removed = false;
		//if x value matches first node of list remove it 
		if(head.data == x)
		{
			head = head.next;
			removed = true;
		}
		//if not the first node check the other nodes
		else
		{
		    Node prev, curr;
			curr = head.next;
			prev = head;
			
			//loop to find a node that matches the value of x 
			while (curr != z && x != curr.data)
			{
			    prev = curr;
				curr = curr.next;
			}
			
			//removes node that matches the value of x from the location found in the while loop
			if (curr != z)
			{
			    prev.next = curr.next;
				removed = true;
			}
		}
		return removed;
    }

	public boolean isEmpty()
	{
       return head == z;
    }
	
    public void display()
    {
		Node t = head;
        System.out.print("\nHead -> ");
		
		while( t != z) {
			System.out.print(t.data + " -> ");
			t = t.next;
		}
		System.out.println("Z\n");
    }
    
    public static void main(String args[])   
    {
        SortedLL list = new SortedLL();
        //list.display();
        
        double x;
        int i, r;
        
        
        for(i = 1; i <= 5; ++i)  
		{
           x =  (Math.random()*100.0);
           r = (int) x; 
           System.out.println("Inserting " + r);
           list.insert(r);
           list.display();            
        }
        
        while(!list.isEmpty())  {
            System.out.println("\nInput a value to remove: ");
            Scanner in = new Scanner(System.in);
            r = in.nextInt();
            if(list.remove(r)) {
                System.out.println("\nSuccessfully removed the item : " + r);
            list.display(); }
            else System.out.println("\nCould not find the value in the list");                        
        }       
    }
}