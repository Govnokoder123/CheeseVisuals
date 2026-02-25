package ru.cheese.mixin.player;

import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.cheese.CheeseVisuals;
import ru.cheese.events.Event;
import ru.cheese.events.impl.input.EventMouse;
import ru.cheese.manager.IMinecraft;
import ru.cheese.manager.Manager;

@Mixin(Mouse.class)
public class MixinMouse implements IMinecraft {
    @Inject(method = "onMouseButton", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;player:Lnet/minecraft/client/network/ClientPlayerEntity;", ordinal = 0))
    private void beforeSpectatorCheck(long window, int button, int action, int mods, CallbackInfo ci) {
        boolean bl = action == 1;
        if (!bl) return;
        CheeseVisuals main = new CheeseVisuals();
        main.keyPress(-100 + button);

        Event.call(new EventMouse(button));
    }
    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (mc.currentScreen instanceof ChatScreen) {
            Manager.DRAG_MANAGER.draggables.values().forEach(dragging -> {
                if (dragging.getModule() != null && dragging.getModule().state) {
                    dragging.onRelease(button);
                }
            });
        }

    }
}