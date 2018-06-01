import java.io.*;

class Heap
{
	 private int[] h;	   // heap array
	 private int[] hPos;	// hPos[h[k]] == k
	 private int[] dist;    // dist[v] = priority of v

	 private int N;         // heap size

	 // The heap constructor gets passed from the Graph:
	 //    1. maximum heap size
	 //    2. reference to the dist[] array
	 //    3. reference to the hPos[] array
	 public Heap(int maxSize, int[] _dist, int[] _hPos) 
	 {
		 N = 0;
		 h = new int[maxSize + 1];
		 dist = _dist;
		 hPos = _hPos;
	 }


	 public boolean isEmpty() 
	 {
		 return N == 0;
	 }


	 public void siftUp( int k) 
	 {
		 int v = h[k];
		
		 h[0] = 0; //Dummy (sentinel) node is placed at the top of the heap
		 dist[0] = Integer.MIN_VALUE;//The distance is set to the smallest value in order to compare
		 
		 
		 //While the current dist element is less than the dist value at element/2
		 while(dist[v] < dist[h[k / 2]]) {
			 //If it isnt found we sift up the value to the next value in hPos
			 h[k] = h[k / 2];
			 hPos[h[k]] = k;
			 //divide the current element by 2 to go the next level up
			 k = k / 2;
		 }
		 //The vertex is then inserted to it's correct position
		 h[k] = v;
		 hPos[v] = k;
		 
	 }


	 public void siftDown( int k) 
	 {
		 int v, j;
		
		 v = h[k];  
		 
		 //Loops until it gets to the end of the array
		 while( k <= N/2) {
			 j = 2 * k;
			 
			 //If the current element is less than the size of the array and distance is greater the next vertex
			 if(j < N && dist[h[j]] > dist[h[j + 1]]){
				 j++;//Increment in order to move to the next element
			 }
			 
			 //If the distance of the current vertex is less than that vertex we sift down
			 if(dist[v] <= dist[h[j]]){
				 break; //then break out of the current operation to stop
			 }
			 //if it isnt < then continue looking further down list
			 h[k] = h[j];
			 hPos[h[k]] = k;
			 k = j;
		 }
		 h[k] = v;
		 hPos[v] = k;
		 
	 }


	 public void insert( int x)
	 {
		 h[++N] = x;
		 siftUp( N);
	 }


	 public int remove()
	 {   
		 int v = h[1];
		 hPos[v] = 0; // v is no longer in heap
		 h[N+1] = 0;  // put null node into empty spot
		 
		 h[1] = h[N--];
		 siftDown(1);
		 
		 return v;
	 }

}

class Graph {
	 class Node {
		 public int vert;
		 public int wgt;
		 public Node next;
	 }
	 
	 // V = number of vertices
	 // E = number of edges
	 // adj[] is the adjacency lists array
	 private int V, E;
	 private Node[] adj;
	 private Node z;
	 private int[] mst;
	 
	 // used for traversing graph
	 private int[] visited;
	 private int id;
	 
	 
	 // default constructor
	 public Graph(String graphFile)  throws IOException
	 {
		 int u, v;
		 int e, wgt;
		 Node t;

		 FileReader fr = new FileReader(graphFile);
		 BufferedReader reader = new BufferedReader(fr);
				   
		 String splits = " +";  // multiple whitespace as delimiter
		 String line = reader.readLine();        
		 String[] parts = line.split(splits);
		 System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
		 
		 V = Integer.parseInt(parts[0]);
		 E = Integer.parseInt(parts[1]);
		 
		 // create sentinel node
		 z = new Node(); 
		 z.next = z;
		 
		 // create adjacency lists, initialised to sentinel node z       
		 adj = new Node[V+1];        
		 for(v = 1; v <= V; ++v)
			 adj[v] = z;               
		 
		 // read the edges
		 System.out.println("Reading edges from text file");
		 for(e = 1; e <= E; ++e)
		 {
			 line = reader.readLine();
			 parts = line.split(splits);
			 u = Integer.parseInt(parts[0]);
			 v = Integer.parseInt(parts[1]); 
			 wgt = Integer.parseInt(parts[2]);
			 
			 System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));    
			 
			 // Code to put edge into adjacency matrix  
			 t = adj[u];
			 adj[u] = new Node();
			 adj[u].vert = v;
			 adj[u].wgt = wgt;
			 adj[u].next = t;
				
				
			 t = adj[v];
			 adj[v] = new Node();
			 adj[v].vert = u;
			 adj[v].wgt = wgt;
			 adj[v].next = t;
			 
		 }	       
	 }

	 // convert vertex into char for pretty printing
	 private char toChar(int u)
	 {  
		 return (char)(u + 64);
	 }
	 
	 // method to display the graph representation
	 public void display() {
		 int v;
		 Node n;
		 
		 for(v=1; v<=V; ++v){
			 System.out.print("\nadj[" + toChar(v) + "] ->" );
			 for(n = adj[v]; n != z; n = n.next){ 
				 System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
			 }
		 }
		 System.out.println("");
	 }


	 
	public void MST_Prim(int s)
	{
		 int loop = 0; 
		 int v, u;
		 int wgt, wgt_sum = 0;
		 int[]  dist, parent, hPos;
		 Node t;

		 parent = new int[V + 1];
		 dist = new int[V + 1];
		 hPos = new int[V + 1];
		 
		 for(v = 0; v <= V; v++) {
			 dist[v] = Integer.MAX_VALUE;//At the start all nodes are presumed to be an infinite distance away
			 parent[v] = 0;//treat 0 as a NULL vertex
			 hPos[v] = 0;
			 
		 }
		 //Heap which is initially empty
		 Heap heap = new Heap(V + 1, dist,hPos);
		 //s = 2 is the root of the MST
		 heap.insert(s); 
		 dist[s] = 0;
		 
		 System.out.println("\n\nStarting at vertex "+ toChar(s) + "\n");
		 while (!heap.isEmpty())
		 {
			 //add v to the MST
			 v = heap.remove();
			 
			 //Shows the construction of the MST
			 System.out.println("Adding edge (" + toChar(parent[v]) + ")--[" + dist[v] + "]--(" + toChar(v) + ")");
			 
			 //adding the current wait to the total in order to calculate the weight of the MST
			 wgt_sum += dist[v];

			 //signals v as now in the MST
			 dist[v] = -dist[v];

			 //loop to continue while the parent node still has children
			 for (t = adj[v]; t != z; t = t.next)
			 {
				 //If the weight of the current vertex is less that 
				 //the value currently in the dist array
				 if (t.wgt < dist[t.vert])
				 {
					 //put the weight into the dist array
					 dist[t.vert] = t.wgt;
					 //Add node to the parrent array
					 parent[t.vert] = v;

					 //If the vertex is NULL then insert next vertex
					 if (hPos[t.vert] == 0)
					 {
						 heap.insert(t.vert);
					 }
					 else //if not null sift up the vertex
					 {
						 heap.siftUp(hPos[t.vert]);
					 }
				 }
			 }
			 System.out.print("Current weight of MST = " + wgt_sum);
			 System.out.print("\n\n");
			 mst = parent;
		}
		System.out.print("Total weight of MST = " + wgt_sum);
	} // end of function MST_Prim()
	 
	 public void showMST()
	 {
			 System.out.print("\n\nMinimum Spanning tree parent array is:\n");
			 for(int v = 1; v <= V; ++v)
				 System.out.println(toChar(v) + " -> " + toChar(mst[v]));
			 System.out.println("");
	}

} // end of class Graph

public class PrimLists {
	 public static void main(String[] args) throws IOException
	 {
		 int s = 2;
         String fname = "MyGraph.txt";  
		 
	     Graph graph = new Graph(fname);   
	     graph.display();
	     graph.MST_Prim(s);
	     graph.showMST();   
	 }
}