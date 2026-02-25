package ru.cheese.modules.misc;

import ru.cheese.modules.setting.BooleanSetting;
import ru.cheese.modules.setting.TextSetting;
import ru.cheese.events.Event;
import ru.cheese.manager.Manager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "NameChanger", desc = "Позволяет визуально поменять имя аккаунта.", type = Type.Misc)
public class NameProtect extends Function {
    public final TextSetting text = new TextSetting("Ник","CheeseVisualsIsGood");
    public final BooleanSetting friend = new BooleanSetting("Менять друзьям",true);

    public NameProtect() {
        addSettings(text,friend);
    }
    public String getCustomName() {
        return Manager.FUNCTION_MANAGER.nameProtect.state ? text.getValue().replaceAll("&", "\u00a7") : mc.getGameProfile().getName();
    }
    public String getProtectedName(String originalName) {
        if (!Manager.FUNCTION_MANAGER.nameProtect.state) return originalName;

        if (isSelf(originalName)) {
            return applyFormatting(text.getValue());
        }

        if (friend.get() && Manager.FRIEND_MANAGER.isFriend(originalName)) {
            return applyFormatting(text.getValue());
        }

        return originalName;
    }
    private String applyFormatting(String name) {
        return name.replace('&', '§');
    }

    private boolean isSelf(String name) {
        return name.equals(mc.getSession().getUsername());
    }
    @Override
    public void onEvent(Event event) {

    }
}
