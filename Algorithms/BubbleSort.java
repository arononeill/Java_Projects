import java.util.Scanner;
 
class BubbleSort {
  public static void main(String []args) {
    int N, i, j, swap;
    Scanner in = new Scanner(System.in);
 
    System.out.println("Input number of integers to sort");
    N = in.nextInt();
 
    int array[] = new int[N];
 
    System.out.println("Enter " + N + " integers");
 
    for (i = 0; i < N; i++) {
      array[i] = in.nextInt();
    }
 
    for (i = 0; i < ( N - 1 ); i++) {
      for (j = 0; j < N - i - 1; j++) {
        /* For descending order use < */
        if (array[j] > array[j+1]) {
          swap       = array[j];
          array[j]   = array[j+1];
          array[j+1] = swap;
        }
      }
    }
 
    System.out.println("Sorted list of numbers");
 
    for (i = 0; i < N; i++) 
      System.out.println(array[i]);
  }
}