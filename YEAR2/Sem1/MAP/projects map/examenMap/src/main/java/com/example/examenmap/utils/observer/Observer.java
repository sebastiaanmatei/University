package com.example.examenmap.utils.observer;


import com.example.examenmap.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
