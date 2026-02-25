package ru.cheese.modules.misc;


import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.util.Formatting;
import ru.cheese.events.Event;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.manager.ClientManager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "DeathCoords", type = Type.Misc,desc = "Отправляет координаты при смерти")
public class DeathCoords extends Function {
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (isPlayerDead()) {
                int positionX = (int) mc.player.getX();
                int positionY = (int) mc.player.getY();
                int positionZ = (int) mc.player.getZ();

                if (mc.player.deathTime < 1) {
                    String message = "Координаты: " + Formatting.GRAY + "X: " + positionX + " Y: " + positionY + " Z: " + positionZ + Formatting.RESET;
                    ClientManager.message(message);
                }
            }
        }
    }

    private boolean isPlayerDead() {
        return mc.player.getHealth() < 1.0f && mc.currentScreen instanceof DeathScreen;
    }
}
