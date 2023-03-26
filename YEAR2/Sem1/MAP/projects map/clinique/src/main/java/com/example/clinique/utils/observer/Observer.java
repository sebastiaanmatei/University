package com.example.clinique.utils.observer;

import com.example.clinique.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
