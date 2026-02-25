package ru.cheese.modules.misc;

import ru.cheese.events.Event;
import ru.cheese.events.impl.move.EventMotion;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.modules.setting.BindBooleanSetting;
import ru.cheese.util.move.MoveUtil;

@SuppressWarnings("All")
@FunctionAnnotation(name = "RWHelper", desc = "Полезная (нет) функция, позволяющая мониторить кулдаун предметов на ReallyWorld (супер WIP)", type = Type.Misc)
public class RWHelper extends Function {

    public RWHelper() {
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof EventMotion)) return;
        if (false || !mc.player.getAbilities().flying) return;
        MoveUtil.setSpeed(1); //а еще я не дам тебе включать спиды на флае)))))
    }
}
