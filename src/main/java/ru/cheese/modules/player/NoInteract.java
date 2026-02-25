package ru.cheese.modules.player;

import ru.cheese.modules.setting.BooleanSetting;
import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "NoWorkbench", desc = "Отключает взаимодействие с верстаками (НИКАКИХ ВЕРСТАКОВ В МОЕМ ДОМЕ, Я ЗАДОЛБАЛСЯ НА НИХ НАЖИМАТЬ)", type = Type.Player)
public class NoInteract extends Function {
    public final BooleanSetting onlyKT = new BooleanSetting("Только в привате (WIP, не работает)",false);

    public NoInteract() {
        addSettings(onlyKT);
    }
    @Override
    public void onEvent(Event event) {

    }
}