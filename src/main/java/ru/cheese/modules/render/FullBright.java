package ru.cheese.modules.render;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import ru.cheese.events.Event;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "FullBright",desc  = "Освещает местность", type = Type.Render)
public class FullBright extends Function {
    private final StatusEffectInstance nightVisionEffect = new StatusEffectInstance(
            StatusEffects.NIGHT_VISION,
            -1,
            255,
            false,
            false,
            true
    );
    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
          mc.player.addStatusEffect(nightVisionEffect,mc.player);
        }
    }

    @Override
    public void onDisable() {
        mc.player.removeStatusEffect(nightVisionEffect.getEffectType());
        super.onDisable();
    }
}