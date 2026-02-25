package ru.cheese.events.impl.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.entity.Entity;
import ru.cheese.events.Event;

@Getter
@RequiredArgsConstructor
public class EventEntitySpawn extends Event {
    private final Entity entity;
}