//Andrew Lucas CSC364 Assignment 3
public class InPlaceIntHeapSort {
    public static void heapSort(int[] array) {
        int temp;
        //Loop that turns a list into a complete binary tree by swapping values based on the parent index value
        for (int i = 1; i < array.length; i++) {
            int parent = (i - 1) / 2; // Parent value is defined
            //Swaps child with parent if child is larger
            if (array[i] > array[parent]) {
                int j = i;
                while (array[j] > array[(j - 1) / 2]) {
                    int jParent = (j - 1) / 2;
                    temp = array[j];
                    array[j] = array[jParent];
                    array[jParent] = temp;
                    j = (j - 1) / 2;
                }
            }
        }
        //Loops backwards almost so we can use index i instead of calculating backwards
        for (int i = array.length - 1; i >= 0; i-- ) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            int current = 0;
            while (current < i) {
                // Indices of children are set
                int leftC = 2 * current + 1;
                int rightC = 2 * current + 2;
                if (leftC >= i) {
                    break;
                    //The tree is a heap if this break statement is reached
                }
                int max = leftC;
                if (rightC < i) {
                    if (array[max] < array[rightC]) {
                        max = rightC;
                    }
                }
                // If current index value is less than the max, swap them
                if (array[current] < array[max]) {
                    temp = array[max];
                    array[max] = array[current];
                    array[current] = temp;
                    current = max;
                }
                else {
                    //Finished
                    break;
                }
            }
        }
    }
}