import java.lang.Math;


public class QuickSort {

    int getRandomNumber(int min, int max) {
        // Get a random number
        return (int) (Math.random() * (max - min)) + min;
    }

    int[] getInitializedRandomArray(int[] arr, int size) {
        // Inserting random elements into the array
        for(int i=0; i<size; i++) {
            arr[i] = this.getRandomNumber(0, 500);
        }

        return arr;
    }

    static void printArray(int[] arr, int size) {
        for(int j=0; j<size; j++) {
            System.out.print(arr[j]);

            // To not print ',' after the final element
            if (j!=size-1) {
                System.out.print(", ");
            }
        }

        System.out.println("");
    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partitionLastElementPivot(int[] arr, int begin, int end, int size) {
        int pivot = arr[end];

        int i = begin - 1;

        for (int j = begin; j <= end - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);

        System.out.println("After partition");
        printArray(arr, size);
        System.out.println("");

        return (i + 1);
    }

    static void quickSortLastElementPivot(int[] arr, int begin, int end, int size) {
        if (begin < end) {
            int pi = partitionLastElementPivot(arr, begin, end, size);

            quickSortLastElementPivot(arr, begin, pi-1, size);

            quickSortLastElementPivot(arr, pi+1, end, size);
        }
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();

        // Worst Case Arrays
        // Sorted in ascending order of length = 14
        int[] arr1 = {15, 22, 31, 45, 66, 70, 82, 91, 102, 111, 125, 140, 178, 195};

        // Sorted in descending order of length = 23
        int[] arr2 = {310, 300, 290, 273, 251, 242, 226, 208, 170, 150, 90, 81, 65, 44, 30, 21, 19, 11, 8, 5};

        System.out.println("Original first array");
        printArray(arr1, arr1.length);

        System.out.println("");
        quickSortLastElementPivot(arr1, 0, arr1.length - 1, arr1.length);

        System.out.println("Sorted first array");
        printArray(arr1, arr1.length);

        System.out.println("===========================================================================================");

        System.out.println("Original second array");
        printArray(arr2, arr2.length);

        System.out.println("");
        quickSortLastElementPivot(arr2, 0, arr2.length - 1, arr2.length);

        System.out.println("Sorted second array");
        printArray(arr2, arr2.length);

        System.out.println("===========================================================================================");


        // Quick sort for random array
        int j = 1;
        int[] randomArray = new int[30]; 
        int randomArrayLength;

        while (j < 4) {
            randomArrayLength = qs.getRandomNumber(10, 20);

            randomArray = qs.getInitializedRandomArray(randomArray, randomArrayLength);


            System.out.println("Original random array: " + j);
            printArray(randomArray, randomArrayLength);
    
            System.out.println("");
            quickSortLastElementPivot(randomArray, 0, randomArrayLength - 1, randomArrayLength);
    
            System.out.println("Sorted random array: " + j);
            printArray(randomArray, randomArrayLength);
    
            System.out.println("===========================================================================================");
            
            j++;
        }
    }
}