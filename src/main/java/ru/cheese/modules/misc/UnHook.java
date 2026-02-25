package ru.cheese.modules.misc;

import org.lwjgl.glfw.GLFW;
import ru.cheese.modules.setting.BindSetting;
import ru.cheese.events.Event;
import ru.cheese.manager.Manager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.screens.unhook.UnHookScreen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@FunctionAnnotation(name = "Unlink",keywords = {"SelfDestruct"}, desc = "Полное отключение всех функций", type = Type.Misc)
public class UnHook extends Function {
    public static BindSetting unHookKey = new BindSetting("Кнопка возврата:", GLFW.GLFW_KEY_INSERT);
    public static final List<Function> functionsToBack = new CopyOnWriteArrayList<>();
    public UnHook() {
        addSettings(unHookKey);
    }


    @Override
    public void onEvent(Event event) {
    }

    @Override
    protected void onEnable() {
        mc.setScreen(new UnHookScreen());
        super.onEnable();
    }

    public void onUnhook() {
        functionsToBack.clear();
        for (int i = 0; i < Manager.FUNCTION_MANAGER.getFunctions().size(); i++) {
            Function function = Manager.FUNCTION_MANAGER.getFunctions().get(i);
            if (function.state && function != this) {
                functionsToBack.add(function);
                function.setState(false);
            }
        }
        File folder = new File("C:\\cheesevisuals");

        if (folder.exists()) {
            try {
                Path folderPathObj = folder.toPath();
                DosFileAttributeView attributes = Files.getFileAttributeView(folderPathObj, DosFileAttributeView.class);
                attributes.setHidden(true);
            } catch (IOException e) {
            }

        }
        toggle();
    }
}
