package runner;

public class SortRunner2 extends AbstractTaskRunner {

    public SortRunner2(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        runner.executeOneTask();
    }

}
