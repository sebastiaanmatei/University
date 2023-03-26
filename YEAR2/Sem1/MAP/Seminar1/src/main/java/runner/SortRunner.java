package runner;

import sort.SortingStrategy;
import sort.SortingTask;

public class SortRunner {


    private SortingTask sortingTask;

    public SortRunner(SortingTask task) {
        this.sortingTask = task;
    }

    public void executeAll() {
       sortingTask.execute();
    }

}
