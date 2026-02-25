package ru.cheese.modules.misc;

import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "NoCommands", desc = "Уберет возможность прописывать команды мода.", type = Type.Misc)
public class NoCommands extends Function {
    public NoCommands() {
    }

    @Override
    public void onEvent(Event event) {

    }
}