package com.example.practice2.utils.observer;


import com.example.practice2.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}

