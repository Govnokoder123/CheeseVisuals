package ru.cheese.modules.render;

import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "ExtraTab",desc  = "Количество игроков табе больше", type = Type.Render)
public class ExtraTab extends Function {

    @Override
    public void onEvent(Event event) {

    }
}