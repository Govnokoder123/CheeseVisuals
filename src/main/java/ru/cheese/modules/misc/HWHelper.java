package ru.cheese.modules.misc;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.modules.setting.BindSetting;
import ru.cheese.modules.setting.BooleanSetting;
import ru.cheese.events.Event;
import ru.cheese.events.impl.input.EventKey;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;
import ru.cheese.util.player.InventoryUtil;
import ru.cheese.util.player.TimerUtil;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("All")
@FunctionAnnotation(name = "HWHelper", desc = "Позволяет забиндить слоты хотбара на кнопки клавиатуры (HolyWorld)", type = Type.Misc)
public class HWHelper extends Function {

    private final BindSetting trapka = new BindSetting("Трапка", 0);
    private final BindSetting trapkaBax = new BindSetting("Взрывная трапка", 0);
    private final BindSetting stan = new BindSetting("Стан", 0);
    private final BindSetting snow = new BindSetting("Ком снега", 0);
    private final BindSetting babax = new BindSetting("Взрывная штучка", 0);

    private final boolean bypass = false; //запрещаем использовать не в хотбаре
    private final boolean inventoryUse = false; //запрещаем использовать не в хотбаре (если поменять на true то игра вылетит)

    private final TimerUtil timer = new TimerUtil();
    private boolean bypassActive = false;
    private boolean awaitingSwap = false;

    private int hotbarSlot = -1;
    private int invSlot = -1;

    private final Map<BindSetting, Item> binds = new LinkedHashMap<>();

    public HWHelper() {
        addSettings(trapka, trapkaBax, stan, snow, babax);
        binds.put(trapka, Items.POPPED_CHORUS_FRUIT);
        binds.put(trapkaBax, Items.PRISMARINE_SHARD);
        binds.put(stan, Items.NETHER_STAR);
        binds.put(snow, Items.SNOWBALL);
        binds.put(babax, Items.FIRE_CHARGE);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventKey eventKey) {
            handleKey(eventKey.key);
        }

        if (event instanceof EventUpdate) {
            handleBypass();
        }
    }

    private void handleKey(int pressedKey) {
        for (Map.Entry<BindSetting, Item> entry : binds.entrySet()) {
            if (pressedKey == entry.getKey().getKey()) {
                int[] slots = findSlots(entry.getValue());

                if (bypass) {
                    bypassActive = false;
                    awaitingSwap = false;
                } else {
                    InventoryUtil.use(slots[0], slots[1], false);
                }
                return;
            }
        }
    }

    private void handleBypass() {
        if (!bypassActive) return;

        setMovementKeys(false);

        if (awaitingSwap && timer.hasTimeElapsed(90)) {
            awaitingSwap = false;
            if (hotbarSlot != -1 || invSlot != -1) {
                InventoryUtil.use(hotbarSlot, invSlot, false);
            }
        }

        if (timer.hasTimeElapsed(150)) {
            bypassActive = false;
            awaitingSwap = false;
            setMovementKeys(true);
        }
    }

    private int[] findSlots(Item item) {
        if (mc.player == null) return new int[]{-1, -1};

        int hotbarSlot = -1;
        int inventorySlot = -1;

        for (int i = 0; i < mc.player.getInventory().size(); i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.isEmpty() || stack.getItem() != item) continue;

            if (i < 9) hotbarSlot = i;
            else inventorySlot = i;

            if (hotbarSlot != -1 && inventorySlot != -1) break;
        }
        return new int[]{hotbarSlot, inventorySlot};
    }

    private void setMovementKeys(boolean restore) {
        KeyBinding[] keys = {
                mc.options.forwardKey,
                mc.options.backKey,
                mc.options.leftKey,
                mc.options.rightKey,
                mc.options.sprintKey
        };

        for (KeyBinding key : keys) {
            if (restore) {
                updateKeyBinding(key);
            } else {
                key.setPressed(false);
            }
        }
    }

    private void updateKeyBinding(KeyBinding keyMapping) {
        keyMapping.setPressed(InputUtil.isKeyPressed(mc.getWindow().getHandle(), keyMapping.getDefaultKey().getCode()));
    }
}
