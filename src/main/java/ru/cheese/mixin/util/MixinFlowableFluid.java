package ru.cheese.mixin.util;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.cheese.manager.Manager;

import static net.minecraft.fluid.FlowableFluid.FALLING;

@Mixin(FlowableFluid.class)
public abstract class MixinFlowableFluid {

    @Shadow
    protected abstract boolean isFlowBlocked(BlockView world, BlockPos pos, Direction direction);

    @Inject(method = "getVelocity", at = @At(value = "HEAD"), cancellable = true)
    private void getVelocityHook(BlockView world, BlockPos pos, FluidState state, CallbackInfoReturnable<Vec3d> cir) {
    }
}