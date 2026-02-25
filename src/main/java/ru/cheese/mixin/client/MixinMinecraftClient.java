package ru.cheese.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.cheese.CheeseVisuals;
import ru.cheese.manager.*;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient implements IMinecraft {
    @Inject(method = "isMultiplayerEnabled", at = @At("HEAD"), cancellable = true)
    private void isMultiplayerEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> cir) {
        if (!ClientManager.legitMode) {
            cir.setReturnValue("CheeseVisuals 1.21.4 | " + MinecraftClient.getInstance().getSession().getUsername());
        }
    }
    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(CallbackInfo ci) {
        CheeseVisuals.getInstance().shutDown();
    }
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        CheeseVisuals.getInstance().init();
    }
}
