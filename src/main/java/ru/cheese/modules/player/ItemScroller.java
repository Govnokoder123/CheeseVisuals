package ru.cheese.modules.player;

import ru.cheese.modules.setting.SliderSetting;
import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "ItemScroll", desc = "Быстрое перемещение", type = Type.Player)
public class ItemScroller extends Function {
    public SliderSetting scroll = new SliderSetting("Задержка", 100f, 1f, 100f,1f);

    public ItemScroller() {
        addSettings(scroll);
    }

    @Override
    public void onEvent(Event event) {

    }
}