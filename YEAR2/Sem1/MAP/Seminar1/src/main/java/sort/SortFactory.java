package sort;

import sort.SortingStrategy;

public class SortFactory {
    public static Sorter createSorter(int[] array, SortingStrategy method){

        if(method==SortingStrategy.QuickSort){
            return new QuickSorter(array);
        }else if(method==SortingStrategy.BubbleSort){
            return new BubbleSorter(array);
        }

        return null;
    }
}
