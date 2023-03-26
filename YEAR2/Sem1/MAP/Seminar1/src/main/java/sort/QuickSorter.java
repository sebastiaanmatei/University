package sort;

public class QuickSorter implements Sorter{
    int[] array;

    public QuickSorter(int[] array) {
        this.array = array;
    }

    static int partition(int numArray[], int low, int high)   {
        int pivot = numArray[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {

            if (numArray[j] <= pivot) {
                i++;

                int temp = numArray[i];
                numArray[i] = numArray[j];
                numArray[j] = temp;
            }
        }

        int temp = numArray[i + 1];
        numArray[i + 1] = numArray[high];
        numArray[high] = temp;
        return i + 1;
    }

    static void quickSort(int numArray[], int low, int high)
    {
        //auxillary stack
        int[] intStack = new int[high - low + 1];
        int top = -1;

        intStack[++top] = low;
        intStack[++top] = high;

        while (top >= 0) {
            high = intStack[top--];
            low = intStack[top--];

            int pivot = partition(numArray, low, high);

            if (pivot - 1 > low) {
                intStack[++top] = low;
                intStack[++top] = pivot - 1;
            }

            if (pivot + 1 < high) {
                intStack[++top] = pivot + 1;
                intStack[++top] = high;
            }
        }
    }



    public void sort(int[] array){
        quickSort(array, 0, array.length-1);

    }
}
