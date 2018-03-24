import java.util.Scanner;
import java.util.Arrays;
 
 
class BinarySearch {

  public static void main(String args[]) {
    char choice = 'y';
    do {
      int i, first, last, middle, N, search, array[];
   
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter number of elements");
      N = sc.nextInt(); 
      array = new int[N];
   
      System.out.println("Enter " + N + " integers");
   
      for (i = 0; i < N; i++) {
        array[i] = sc.nextInt();
      }

      Arrays.sort(array);

      System.out.println("\n The Array Rearanged from smallest to largest is as follows: \n");
      //for loop to print Array values to console
      for (i = 0; i < N; i++) {
        System.out.println(array[i]);
      }
   
      System.out.println("Enter value to find");
      search = sc.nextInt();
   
      first  = 0;
      last   = N - 1;
      middle = (first + last)/2;
   
      while( first <= last ) {

        if ( array[middle] < search ) { // checks to see which half of the array the number being searched for is sc
          first = middle + 1;    
        }

        else if ( array[middle] == search ) { // checks to see if the number searched for is at the middle of the array
          System.out.println(search + " found at location " + (middle + 1) + ".");
          break;
        }

        else {
           last = middle - 1;
        }
        middle = (first + last)/2;
      }

      if ( first > last ) {
        System.out.println(search + " isn't present in the list.\n");
      }

      System.out.println("If you wish to continue press y otherwise press any other char");
      choice = sc.next().charAt(0);
    }
    while (choice == 'y');
  }
}