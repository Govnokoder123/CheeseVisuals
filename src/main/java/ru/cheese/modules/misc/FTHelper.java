package ru.cheese.modules.misc;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.entity.player.PlayerInventory;
import ru.cheese.modules.setting.BindSetting;
import ru.cheese.events.Event;
import ru.cheese.events.impl.input.EventKey;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.modules.setting.BooleanSetting;
import ru.cheese.util.player.InventoryUtil;

import java.util.LinkedHashMap;
import java.util.Map;

@FunctionAnnotation(name = "FTHelper", desc = "Позволяет забиндить слоты хотбара на кнопки клавиатуры (Funtime)", type = Type.Misc)
public class FTHelper extends Function {
    private final BindSetting trapka = new BindSetting("Трапка", 0);
    private final BindSetting disorientation = new BindSetting("Дезориентация", 0);
    private final BindSetting plast = new BindSetting("Пласт", 0);
    private final BindSetting godaura = new BindSetting("Божья аура", 0);
    private final boolean inventoryUse = false; //здесь тоже запрещаем использование из инва (да я тварь, и если это поменять, при запуске вылетит игра)

    private final Map<BindSetting, Item> binds = new LinkedHashMap<>();

    public FTHelper() {
        addSettings(trapka, disorientation, plast, godaura);
        binds.put(trapka, Items.NETHERITE_SCRAP);
        binds.put(disorientation, Items.ENDER_EYE);
        binds.put(plast, Items.DRIED_KELP);
        binds.put(godaura, Items.PHANTOM_MEMBRANE);
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof EventKey eventKey) || mc.player == null) return;

        int pressedKey = eventKey.key;
        for (Map.Entry<BindSetting, Item> entry : binds.entrySet()) {
            if (pressedKey == entry.getKey().getKey()) {
                useItem(entry.getValue());
                return;
            }
        }
    }

    private void useItem(Item item) {
        int[] slots = findSlots(item);
        InventoryUtil.use(slots[0], slots[1],false);
    }

    private int[] findSlots(Item item) {
        if (mc.player == null) return new int[]{-1, -1};
        PlayerInventory inv = mc.player.getInventory();
        int size = inv.size();
        int hotbarSlot = -1;
        int inventorySlot = -1;

        for (int i = 0; i < size; i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() == item) {
                if (i < 9) {
                    if (hotbarSlot == -1) hotbarSlot = i;
                    if (inventorySlot == -1) inventorySlot = i + 36;
                } else {
                    if (inventorySlot == -1) inventorySlot = i;
                }
                if (hotbarSlot != -1 && inventorySlot != -1) break;
            }
        }
        return new int[]{hotbarSlot, inventorySlot};
    }
}