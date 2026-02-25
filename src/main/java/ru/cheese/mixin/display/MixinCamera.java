package ru.cheese.mixin.display;

import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.cheese.manager.IMinecraft;
import ru.cheese.manager.Manager;
import ru.cheese.modules.movement.freelook.CameraOverriddenEntity;
import ru.cheese.modules.movement.freelook.FreeLookState;

@Mixin(Camera.class)
public abstract class MixinCamera implements IMinecraft {
    @Shadow
    protected abstract void setRotation(float yaw, float pitch);

    @Unique
    private boolean initialized = false;
    @Shadow
    private boolean thirdPerson;
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V", shift = At.Shift.AFTER))
    private void onUpdate(CallbackInfo ci) {
        if (!FreeLookState.active || !(mc.player instanceof CameraOverriddenEntity entity))
            return;

        if (!initialized) {
            entity.setCameraPitch(mc.player.getPitch());
            entity.setCameraYaw(mc.player.getYaw());
            initialized = true;
        }

        setRotation(entity.getCameraYaw(), entity.getCameraPitch());
    }
}