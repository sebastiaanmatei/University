package container;
import model.Task;
import static utils.Constants.INITIAL_STACK_SIZE;

public class QueueContainer extends AbstractContainer{

    public QueueContainer() {
        super();
    }

    @Override
    public Task remove() {
        if(!isEmpty()) {
            Task t=tasks[0];
            for(int i=1;i<size;i++){
                tasks[i-1]=tasks[i];
            }
            size--;
            return t;
        }
        return null;
    }

}
