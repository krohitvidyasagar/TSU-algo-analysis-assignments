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

    static int partition(int[] arr, int begin, int end) {
        int temp;
        int pivot = arr[end];

        int i = begin - 1;

        for (int j = begin; j <= end - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);
        return (i + 1);
    }

    static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int pi = partition(arr, begin, end);

            quickSort(arr, begin, pi-1);

            quickSort(arr, pi+1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 5, 8, 9, 2, 3, 6, 11};

        int arr_size = arr.length;

        quickSort(arr, 0, arr_size - 1);

        printArray(arr, arr_size);
    }
}