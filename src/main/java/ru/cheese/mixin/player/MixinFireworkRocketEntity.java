package ru.cheese.mixin.player;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.cheese.manager.IMinecraft;
import ru.cheese.manager.Manager;
import ru.cheese.modules.combat.rotation.RotationController;

@Mixin(FireworkRocketEntity.class)
public abstract class MixinFireworkRocketEntity implements IMinecraft {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void tick(LivingEntity shooter, Vec3d originalVelocity) {
        if (shooter.isGliding() && shooter == mc.player) {
            Vec3d rotationVector;
            RotationController rotationController = Manager.ROTATION;
            rotationVector = shooter.getRotationVecClient();

            double speedXZ = 1.5;
            double speedY = 1.5;
}}}
