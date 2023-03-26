package Facebook.utils.observer;


import Facebook.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}