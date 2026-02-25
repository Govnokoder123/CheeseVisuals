package ru.cheese.mixin.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.cheese.manager.IMinecraft;
import ru.cheese.manager.Manager;
import ru.cheese.modules.combat.rotation.RotationController;
import ru.cheese.modules.movement.freelook.CameraOverriddenEntity;
import ru.cheese.modules.movement.freelook.FreeLookState;
import ru.cheese.modules.render.Trails;
import ru.cheese.util.IEntity;
import ru.cheese.util.player.AuraUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("All")
@Mixin(Entity.class)
public abstract class MixinEntity implements IEntity, CameraOverriddenEntity, IMinecraft {

    @Unique
    private float cameraYaw;
    @Unique
    private float cameraPitch;

    @Unique
    private List<Trails.Trail> trails = new ArrayList<>();
    @Unique
    private Vec3d lastTrailPos;

    @Shadow
    private Box boundingBox;

    @Shadow
    protected static Vec3d movementInputToVelocity(Vec3d movementInput, float speed, float yaw) {
        double d = movementInput.lengthSquared();
        if (d < 1.0E-7) {
            return Vec3d.ZERO;
        } else {
            Vec3d vec3d = (d > 1.0F ? movementInput.normalize() : movementInput).multiply(speed);
            float sin = MathHelper.sin(yaw * ((float) Math.PI / 180F));
            float cos = MathHelper.cos(yaw * ((float) Math.PI / 180F));
            return new Vec3d(vec3d.x * cos - vec3d.z * sin, vec3d.y, vec3d.z * cos + vec3d.x * sin);
        }
    }

    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    private void onChangeLookDirection(double deltaX, double deltaY, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        if (FreeLookState.active && self instanceof ClientPlayerEntity) {
            cameraYaw += (float) deltaX * 0.15F;
            cameraPitch = MathHelper.clamp(cameraPitch + (float) deltaY * 0.15F, -90.0F, 90.0F);
            ci.cancel();
        }
    }

    @Override
    public float getCameraPitch() {
        return cameraPitch;
    }

    @Override
    public float getCameraYaw() {
        return cameraYaw;
    }

    @Override
    public void setCameraPitch(float pitch) {
        cameraPitch = pitch;
    }

    @Override
    public void setCameraYaw(float yaw) {
        cameraYaw = yaw;
    }

    @ModifyArgs(method = "pushAwayFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    private void pushAwayFromHook(Args args) {
        Entity self = (Entity)(Object)this;
    }

    @Override
    public List<Trails.Trail> exosWareFabric1_21_4$getTrails() {
        return trails;
    }

    @Override
    public Vec3d exosWareFabric1_21_4$getLastTrailPos() {
        return lastTrailPos;
    }

    @Override
    public void exosWareFabric1_21_4$setLastTrailPos(Vec3d pos) {
        this.lastTrailPos = pos;
    }

    @ModifyExpressionValue(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isControlledByPlayer()Z"))
    private boolean fixFallDistanceCalculation(boolean original) {
        Entity self = (Entity)(Object)this;
        return self != mc.player && original;
    }

    @Inject(method = "getBoundingBox", at = @At("RETURN"), cancellable = true)
    private void getBoundingBox(CallbackInfoReturnable<Box> cir) {
        Entity self = (Entity)(Object)this;
    }

    @Inject(method = "updateVelocity", at = @At("HEAD"), cancellable = true)
    private void onUpdateVelocity(float speed, Vec3d movementInput, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        if (self != mc.player) return;

        Vec3d customVelocity = null;

        RotationController rotationController = Manager.ROTATION;

        if (customVelocity != null) {
            self.setVelocity(self.getVelocity().add(customVelocity));
            ci.cancel();
        }
    }

}
