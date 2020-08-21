//Andrew Lucas, This program reads in 10 numbers and then gives back the distinct values
import java.util.Scanner;
import java.util.Arrays; //allows me to use the Arrays.toString() Method
public class Distinct {
    public static void main(String[] args){
        int[] myList = new int[10];
        int disCount = 0;
        int temp;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter 10 integer values.");
        for (int i = 1; i<=10; i++){ //Gather ten numbers
            System.out.print("Enter value: ");
            myList[i-1] = input.nextInt();
        }
        input.close();
        int[] distinct = new int[1];
        distinct[0] = myList[0];
        int i = 0;
        boolean b = false;
        for (; i<=9; i++){ //Test each number to see if its distinct
            temp = myList[i];
            for (int j = i;j<=9;j++) {
                if (temp == myList[j]){
                    b = false;
                    break;
                }
                else
                    b = true;
            }
            if(b == true) {
                distinct = Arrays.copyOf(distinct, distinct.length + 1);
                //Creates a new list of the same values but adds that temps to the end
                distinct[distinct.length - 1] = temp;
            }
        }
        System.out .print("The values you entered are: ");
        System.out.println(Arrays.toString(myList));
        System.out .print("The distinct values are: ");
        System.out.println(Arrays.toString(distinct));
    }
}
