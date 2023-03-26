package factory;

import container.Container;
import container.QueueContainer;
import container.StackContainer;
import container.Strategy;

public class TaskContainerFactory implements Factory {

    private Container stackContainer;
    private Container queueContainer;

    public TaskContainerFactory() {
    }
    // TO DO : Singleton


    @Override
    public Container createContainer(Strategy strategy) {
            Container container = null;
            if (strategy == Strategy.LIFO) {
                stackContainer = new StackContainer();
                container = stackContainer;
            }
            if (strategy == Strategy.FIFO) {
                queueContainer = new QueueContainer();
                container = queueContainer;
            }
            return container;
    }
}
