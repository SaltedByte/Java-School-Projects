/* Requests value n from the user.
 * Generates an nxn matrix of random values chosen from {0,1}.
 * Reports which rows, columns, and diagonals in the matrix
 * have the same value throughout.
 * For example, if row 2 consists of only 1's, then the message
 * "All 1s on row 2" is displayed.
 * Authors: Jeff Ward and
 */

import java.util.*;

public class ExploreMatrix {
    // Note:  Do not modify the following main method.
    // Instead, modify the following method stubs so that the main
    // method works as is.
    public static void main(String[] args) {
        System.out.print("Enter the size of the matrix: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        int[][] matrix = randomZeroOneMatrix(n);
        printTwoDArray(matrix);
        rowChecks(matrix);
        columnChecks(matrix);
        checkMajorDiagonal(matrix);
        checkSubDiagonal(matrix);

    }

    // Check which rows have all of the same entries.
    // Display a message for each such row.
    // Display a different message if none of the rows
    // have all the same entries.
    // Precondition:  none of the entries are -1.
    public static void rowChecks(int[][] matrix) {
        int test = 0;
        boolean same = false;
        for(int i = 0; i < matrix.length; i++) {
            test = checkRowForSameness(matrix, i);
            if (test == 1){
                System.out.println("All  1's on row " + i + ".");
                same = true;
            }
            if (test == 0) {
                System.out.println("All  0's on row " + i + ".");
                same = true;
            }
        }
        if (same == false)
            System.out.println("Not same numbers on a row.");
    }

    // Check which columns have all of the same entries.
    // Display a message for each such column.
    // Display a different message if none of the columns
    // have all the same entries.
    // Precondition:  none of the entries are -1.
    public static void columnChecks(int[][] matrix) {
        for(int j = 0; j<matrix.length; j++)
            checkColumnForSameness(matrix, j);
        int test = 0;
        boolean same = false;
        for(int j = 0; j < matrix.length; j++) {
            test = checkColumnForSameness(matrix, j);
            if (test == 1) {
                System.out.println("All  1's on column " + j + ".");
                same = true;
            }
            if (test == 0) {
                System.out.println("All  1's on column " + j + ".");
                same = true;
            }
        }
        if (same == false)
            System.out.println("Not same numbers on a column.");

    }

    // Display the array values.
    public static void printTwoDArray(int[][] array) {
        for(int i = 0; i<array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Generate an nxn matrix randomly filled
    // with zeroes and ones.
    public static int[][] randomZeroOneMatrix(int n) {
        int [][] matrix = new int [n][n];
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                matrix[i][j] = (int)(Math.random() * 2.0);
        return matrix;
    }

    // If all values in row i are the same, then return that value.
    // Otherwise, return -1.
    public static int checkRowForSameness(int[][] matrix, int i) {
        if (matrix[i][0]==1){
            boolean rSame = true;
            for(int j = 1; j < matrix.length; j++){
                if (matrix[i][j] == 1){
                }
                else{
                    rSame = false;
                }
            }
            if (rSame){
                return 1;
            }
            else{
                return -1;
            }
        }
        if (matrix[i][1]==0){
            boolean rSame = true;
            for(int j = 1; j < matrix.length; j++){
                if (matrix[i][j] == 0){
                }
                else{
                    rSame = false;
                }
            }
            if (rSame){
                return 0;
            }
            else{
                return -1;
            }
        }
        return -1; // stub
    }

    // If all values in col j are the same, then return that value.
    // Otherwise, return -1.
    public static int checkColumnForSameness(int[][] matrix, int j) {
        if (matrix[0][j] == 1) {
            boolean cSame = true;
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][j] == 1) {
                } else {
                    cSame = false;
                }
            }
            if (cSame) {
                return 1;
            } else {
                return -1;
            }
        }
        if (matrix[0][j] == 0) {
            boolean cSame = true;
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][j] == 0) {
                } else {
                    cSame = false;
                }
            }
            if (cSame) {
                return 0;
            }
            else {
                return -1;
            }
        }
        return -1; // stub
    }

    // Check whether all values along the major diagonal
    // of the matrix are the same.
    // Print a message accordingly.
    public static void checkMajorDiagonal(int[][] matrix) {
        System.out.println("checkMajorDiagonal stub");
    }

    // Check whether all values along the sub-diagonal
    // of the matrix are the same.
    // Print a message accordingly.
    public static void checkSubDiagonal(int[][] matrix)	{
        System.out.println("checkMajorDiagonal stub");
    }
}
