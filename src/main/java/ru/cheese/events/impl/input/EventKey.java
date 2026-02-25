package ru.cheese.events.impl.input;


import ru.cheese.events.Event;

public class EventKey extends Event {
    public int key;

    public EventKey(int key) {
        this.key = key;
    }
}
