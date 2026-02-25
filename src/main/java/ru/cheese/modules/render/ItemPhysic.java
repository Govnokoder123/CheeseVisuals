package ru.cheese.modules.render;

import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.modules.setting.ModeSetting;

@FunctionAnnotation(name = "ItemPhysic",desc  = "Красиво лежат предметы на земле", type = Type.Render)
public class ItemPhysic extends Function {

    public final ModeSetting mode = new ModeSetting("Физика","Обычная","Обычная","2D");
    public ItemPhysic() {
        addSettings(mode);
    }

    @Override
    public void onEvent(Event event) {
    }
}