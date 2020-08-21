//Andrew Lucas Homework 2 FilesInDir
package HW2;
import java.io.File;
import java.util.Scanner;
public class FilesInDir {
    //ask for some damn file
    public static void main(String[] Args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a folder location: ");
        String directory = input.nextLine();
        System.out.println("Files: " + findSize(new File(directory)));
    }
    public static long findSize(File file){
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0;i < files.length; i++) {
                size += findSize(files[i]);
            }
        }
        else {
            size += 1;
        }
        return size;
    }
}