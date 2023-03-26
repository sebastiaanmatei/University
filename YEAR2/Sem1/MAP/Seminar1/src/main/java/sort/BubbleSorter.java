package sort;


public class BubbleSorter implements Sorter{
    int[] array;

    public BubbleSorter(int[] array) {
        this.array = array;
    }

    public void sort(int[] array){

        for(int i=0;i< array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int c=array[j];
                    array[j]=array[i];
                    array[i]=c;
                }
            }
        }

    }

}
