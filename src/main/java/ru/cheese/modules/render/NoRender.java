package ru.cheese.modules.render;

import ru.cheese.modules.setting.MultiSetting;
import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

import java.util.Arrays;

@FunctionAnnotation(name = "NoRender", type = Type.Render, desc = "Убирает разные типы на экране")
public class NoRender extends Function {
    public MultiSetting mods = new MultiSetting(
            "Убрать",
            Arrays.asList("Тряска камеры", "Огонь на экране", "Вода на экране","Удушье","Плохие эффекты"),
            new String[]{"Тряска камеры", "Огонь на экране", "Вода на экране", "Удушье", "Скорборд","Плохие эффекты"}
    );

    public NoRender() {
        addSettings(mods);
    }
    @Override
    public void onEvent(Event event) {

    }
}