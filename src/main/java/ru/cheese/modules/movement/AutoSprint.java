package ru.cheese.modules.movement;

import ru.cheese.events.Event;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "AutoSprint" ,desc  = "Автоматически включает бег", type = Type.Move)
public class AutoSprint extends Function {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            mc.options.sprintKey.setPressed(true);
        }
    }

    @Override
    protected void onDisable() {
        mc.options.sprintKey.setPressed(false);
        super.onDisable();
    }
}