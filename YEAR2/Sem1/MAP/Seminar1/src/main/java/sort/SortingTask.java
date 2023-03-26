package sort;
import model.Task;

public class SortingTask extends Task{

    int[] array;
    SortingStrategy method;

    public SortingTask(String taskId, String description, int[] array, SortingStrategy method) {
        super(taskId, description);
        this.array = array;
        this.method = method;
    }


    @Override
    public void execute() {
        Sorter srt=SortFactory.createSorter(array, method);
        srt.sort(array);
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

}
