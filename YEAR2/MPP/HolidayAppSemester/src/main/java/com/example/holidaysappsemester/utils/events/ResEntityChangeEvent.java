package com.example.holidaysappsemester.utils.events;


import com.example.holidaysappsemester.domain.Reservation;

public class ResEntityChangeEvent implements Event {
    private com.example.holidaysappsemester.utils.events.ChangeEventType type;
    private Reservation data, oldData;

    public ResEntityChangeEvent(com.example.holidaysappsemester.utils.events.ChangeEventType type, Reservation data) {
        this.type = type;
        this.data = data;
    }
    public ResEntityChangeEvent(com.example.holidaysappsemester.utils.events.ChangeEventType type, Reservation data, Reservation oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public com.example.holidaysappsemester.utils.events.ChangeEventType getType() {
        return type;
    }

    public Reservation getData() {
        return data;
    }

    public Reservation getOldData() {
        return oldData;
    }
}