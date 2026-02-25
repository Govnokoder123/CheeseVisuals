package ru.cheese.modules.movement;

import net.minecraft.client.option.Perspective;
import ru.cheese.modules.setting.BindSetting;
import ru.cheese.events.Event;
import ru.cheese.events.impl.input.EventKey;
import ru.cheese.manager.Manager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.modules.movement.freelook.FreeLookState;
import ru.cheese.manager.ClientManager;

@FunctionAnnotation(name = "FreeLook", desc = "Позволит вращать камеру, при этом не меняя направления движения", type = Type.Move)
public class FreeLook extends Function {
    private final BindSetting bind = new BindSetting("Кнопка", 0);
    private Perspective previousPerspective;

    public FreeLook() {
        addSettings(bind);
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof EventKey keyEvent)) return;
        if (keyEvent.key != bind.getKey()) return;
        if (mc == null || mc.options == null) return;

        FreeLookState.active = !FreeLookState.active;

        if (FreeLookState.active) {
            previousPerspective = mc.options.getPerspective();
            if (previousPerspective != Perspective.THIRD_PERSON_FRONT) {
                mc.options.setPerspective(Perspective.THIRD_PERSON_FRONT);
            }
        } else {
            mc.options.setPerspective(previousPerspective != null ? previousPerspective : Perspective.FIRST_PERSON);
        }
    }
    @Override
    public void onDisable() {
        FreeLookState.active = false;
        if (mc != null && mc.options != null) {
            mc.options.setPerspective(previousPerspective != null ? previousPerspective : Perspective.FIRST_PERSON);
        }
        previousPerspective = null;
    }

}