package container;

import model.Task;

import static utils.Constants.INITIAL_STACK_SIZE;

public class StackContainer extends AbstractContainer {

    public StackContainer() {
        super();
    }

    @Override
    public Task remove() {
        if(!isEmpty()) {
            size--;
            return tasks[size];
        }
        return null;
    }


}
