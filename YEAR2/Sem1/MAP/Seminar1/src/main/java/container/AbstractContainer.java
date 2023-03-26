package container;

import model.Task;

import static utils.Constants.INITIAL_STACK_SIZE;

public abstract class AbstractContainer implements Container {

    Task[] tasks;
    int size;

    public AbstractContainer() {
        size = 0;
        tasks = new Task[INITIAL_STACK_SIZE];
    }

    @Override
    public void add(Task task) {
        if (tasks.length == size) {
            Task[] t = new Task[tasks.length * 2];
            System.arraycopy(tasks, 0, t, 0, tasks.length);
            tasks = t;
        }
        tasks[size] = task;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
