//Andrew Lucas CSC364 Knapsack Assignment 2
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {

    public static void main(String Args[]) throws FileNotFoundException {
        //Grab the initial inputs needed
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of available employee work weeks: ");
        int workWeeks = input.nextInt();
        System.out.print("Enter the name of input file: ");
        String source = input.next();
        System.out.print("Enter the name of output file: ");
        String output = input.next();
        input.close();
        File sourceFile = new File(source);

        //Gather the contents of the file
        Scanner injector = new Scanner(sourceFile);
        ArrayList<Project> list = new ArrayList<>();
        while (injector.hasNextLine()) {
            String line = injector.nextLine();
            String[] split = line.split(" ");
            list.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
        injector.close();
        System.out.println("Number of Projects: " + list.size());

        //Make the Matrix thing
        System.out.println(list.get(2).labor);
        int[][] matrix = new int[workWeeks + 1][list.size() + 1];
        for (int i = 1; i < list.size() + 1; i++) {
            for (int j = 1; j < workWeeks + 1; j++) {
                if (list.get(i - 1).labor > j) {
                    matrix[j][i] = matrix[j][i - 1];
                }
                else {
                    matrix[j][i] = Math.max(matrix[j - list.get(i - 1).labor][i - 1] + list.get(i - 1).profit, matrix[j][i - 1]);
                }
            }
        }

        //To print out the matrix for testing
        /*for (int[] z : matrix) {
            System.out.println(Arrays.toString(z));
        }*/

        //here is where we have to actually read the matrix...
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int row = workWeeks;
        int col = list.size();
        int cur= matrix[row][col];
        while (cur!= 0) {
            if (matrix[row][col] != matrix[row][col - 1]) {
                answer.add(col - 1);
                cur = matrix[row - (list.get(col - 1).labor)][col];
                row = row - list.get(col -1).labor;
                col--;
            }
            else {
                cur = matrix[row][col - 1];
                col--;
            }
        }
        int total = 0;
        for (int i = 0; i <= answer.size() - 1; i++) {
            total += list.get(answer.get(i)).profit;
        }

        //Writes the file
        PrintWriter Writer = new PrintWriter(output);
        Writer.println("Number of projects available: " + list.size() + " projects");
        Writer.println("Available employee work weeks: " + workWeeks + " weeks");
        Writer.println("Number of projects chosen: " + answer.size());
        Writer.println("Total profit: $" + total);

        for (int i = answer.size() - 1; i >= 0; i--) {
            Writer.println(list.get(answer.get(i)).name + " " + list.get(answer.get(i)).labor + " " + list.get(answer.get(i)).profit);
        }

        //Done and tidy
        Writer.close();
        System.out.println("Done");
    }
    //object constructor to store the projects
    private static class Project {
        String name;
        int labor;
        int profit;

        public Project(String name, int labor, int profit) {
            this.name = name;
            this.labor = labor;
            this.profit = profit;
        }
    }
}