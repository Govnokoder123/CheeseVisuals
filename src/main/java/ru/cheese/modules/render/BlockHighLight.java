package ru.cheese.modules.render;

import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import ru.cheese.events.Event;
import ru.cheese.events.impl.render.EventRender3D;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.util.color.ColorUtil;
import ru.cheese.util.render.Render3DUtil;

@FunctionAnnotation(name = "BlockHighLight", desc = "Подсвечивает текущий блок, на который ты навёлся", type = Type.Render)
public class BlockHighLight extends Function {

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof EventRender3D e)) return;
        if (!(mc.crosshairTarget instanceof BlockHitResult result)) return;
        if (result.getType() != HitResult.Type.BLOCK) return;
        BlockPos pos = result.getBlockPos();
        if (pos == null) return;
        Render3DUtil.drawShapeAlternative(pos, mc.world.getBlockState(pos).getOutlineShape(mc.world, pos), ColorUtil.getColorStyle(360), 2, true, true);

    }
}
