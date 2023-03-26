package com.example.clinique.utils.events;

import com.example.clinique.domain.Checkup;

public class CheckupEntityChangeEvent implements Event {
    private com.example.clinique.utils.events.ChangeEventType type;
    private Checkup data, oldData;

    public CheckupEntityChangeEvent(com.example.clinique.utils.events.ChangeEventType type, Checkup data) {
        this.type = type;
        this.data = data;
    }
    public CheckupEntityChangeEvent(com.example.clinique.utils.events.ChangeEventType type, Checkup data, Checkup oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public com.example.clinique.utils.events.ChangeEventType getType() {
        return type;
    }

    public Checkup getData() {
        return data;
    }

    public Checkup getOldData() {
        return oldData;
    }
}
