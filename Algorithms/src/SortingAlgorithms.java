/**
 * Given sequence 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, sort the sequence using the following
 algorithms, and illustrate the details of the execution of the algorithms:
 a. merge-sort algorithm.
 Computer Science 272: Data Structures and Algorithms Page 1 of 2
 b.quick-sort algorithm. Choose a partitioning strategy you like to pick a pivot element
 from the sequence. Analyze how different portioning strategies may impact on the performance
 of the sorting algorithm.
 *
 *
 **/

import java.util.Arrays;
public class SortingAlgorithms {

    /**
     * Sorts an array using the merge sort algorithm.
     *
     * @param array The array to be sorted.
     */
    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Helper method to recursively sort sub-arrays using merge sort.
     *
     * @param array The array to be sorted.
     * @param left  The index of the leftmost element of the sub-array.
     * @param right The index of the rightmost element of the sub-array.
     */
    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle); // Sort left half
            mergeSort(array, middle + 1, right); // Sort right half
            merge(array, left, middle, right); // Merge sorted halves
        }
    }

    /**
     * Merges two sub-arrays into one sorted array.
     *
     * @param array  The array containing both sub-arrays.
     * @param left   The index of the leftmost element of the first sub-array.
     * @param middle The index of the rightmost element of the first sub-array.
     * @param right  The index of the rightmost element of the second sub-array.
     */
    private static void merge(int[] array, int left, int middle, int right) {
        int[] tempArray = new int[array.length];
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left; // Index for the left sub-array
        int j = middle + 1; // Index for the right sub-array
        int k = left; // Index for merged array

        while (i <= middle && j <= right) {
            if (tempArray[i] <= tempArray[j]) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = tempArray[i];
            i++;
            k++;
        }
    }

    /**
     * Sorts an array using the quick sort algorithm.
     *
     * @param array The array to be sorted.
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Helper method to recursively sort sub-arrays using quick sort.
     *
     * @param array The array to be sorted.
     * @param low   The index of the leftmost element of the sub-array.
     * @param high  The index of the rightmost element of the sub-array.
     */
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high); // Partition the array
            quickSort(array, low, pivotIndex - 1); // Sort left sub-array
            quickSort(array, pivotIndex + 1, high); // Sort right sub-array
        }
    }

    /**
     * Partitions the array and returns the index of the pivot element.
     *
     * @param array The array to be partitioned.
     * @param low   The index of the leftmost element of the sub-array.
     * @param high  The index of the rightmost element of the sub-array.
     * @return The index of the pivot element after partitioning.
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Choose the last element as the pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i + 1] and array[high] (pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    /**
     * Main method to test merge sort and quick sort algorithms.
     */
    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("Original Array: " + Arrays.toString(array));

        int[] arrayMergeSort = Arrays.copyOf(array, array.length);
        mergeSort(arrayMergeSort);
        System.out.println("Array after Merge Sort: " + Arrays.toString(arrayMergeSort));

        int[] arrayQuickSort = Arrays.copyOf(array, array.length);
        quickSort(arrayQuickSort);
        System.out.println("Array after Quick Sort: " + Arrays.toString(arrayQuickSort));
    }
}
