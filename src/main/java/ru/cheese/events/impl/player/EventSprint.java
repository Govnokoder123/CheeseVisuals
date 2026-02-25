package ru.cheese.events.impl.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.cheese.events.Event;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EventSprint extends Event {
    private boolean sprinting;
}