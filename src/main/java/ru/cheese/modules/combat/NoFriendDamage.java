package ru.cheese.modules.combat;


import ru.cheese.events.Event;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "NoFriendDamage", keywords = {"NFD","FriendDamage"}, type = Type.Combat, desc = "Отключает урон по друзьям")
public class NoFriendDamage extends Function {
    public NoFriendDamage() {
        addSettings();
    }
    @Override
    public void onEvent(Event event) {
    }
}
