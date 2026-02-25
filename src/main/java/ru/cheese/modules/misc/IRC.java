package ru.cheese.modules.misc;

import ru.cheese.events.Event;
import ru.cheese.manager.Manager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "IRC", desc = "Бесполезный онлайн-чат между юзерами всего что можно и нельзя юзать.", type = Type.Misc)
public class IRC extends Function {

    @Override
    public void onEvent(Event event) {
    }


    @Override
    protected void onDisable() {
        Manager.IRC_MANAGER.shutdown();
        super.onDisable();
    }
}