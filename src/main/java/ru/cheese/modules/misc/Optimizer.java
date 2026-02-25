package ru.cheese.modules.misc;

import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import ru.cheese.modules.setting.BooleanSetting;
import ru.cheese.events.Event;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.util.player.TimerUtil;

@FunctionAnnotation(name = "Optimizer", desc = "(Если нету оптимизирующих модов) Чистит оперативную память, использующуюся игрой. Должна увеличивать производительность.", type = Type.Misc)
public class Optimizer extends Function {

    private final BooleanSetting memory = new BooleanSetting("Free memory", true);
    private final BooleanSetting graphics = new BooleanSetting("Low graphics", true);
    private final BooleanSetting boostFPS = new BooleanSetting("Max FPS", true);

    private final TimerUtil timerHelper = new TimerUtil();

    public Optimizer() {
        addSettings(memory, graphics, boostFPS);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            if (memory.get() && timerHelper.hasTimeElapsed(300000)) {
                System.gc();
                Runtime.getRuntime().freeMemory();
                timerHelper.reset();
            }

            if (graphics.get() && mc.world != null) {
                mc.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
                mc.options.getGraphicsMode().setValue(GraphicsMode.FAST);
            }

            if (boostFPS.get()) {
                mc.options.getEnableVsync().setValue(false);
                mc.options.getMaxFps().setValue(260);
            }
        }
    }
}
