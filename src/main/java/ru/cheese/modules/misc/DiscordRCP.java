package ru.cheese.modules.misc;

import ru.cheese.com.discord.DiscordEventHandlers;
import ru.cheese.com.discord.DiscordRPC;
import ru.cheese.com.discord.DiscordRichPresence;
import ru.cheese.events.Event;
import ru.cheese.events.impl.EventUpdate;
import ru.cheese.manager.Manager;
import ru.cheese.modules.Function;
import ru.cheese.modules.FunctionAnnotation;
import ru.cheese.modules.Type;

@FunctionAnnotation(name = "DiscordRPC", desc = "Показывает что ты крутой и играешь с визуалами от Крашера", type = Type.Misc)
public class DiscordRCP extends Function {
    private final DiscordRPC rpc = DiscordRPC.INSTANCE;
    private volatile boolean started = false;
    private Thread thread;
    private final DiscordRichPresence presence = new DiscordRichPresence();

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            startRpc();
        }
    }

    public synchronized void startRpc() {
        if (started) return;
        started = true;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("1384873696375603281", handlers, true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;
        presence.largeImageText = "https://github.com/Govnokoder123";

        updatePresenceFields();

        rpc.Discord_UpdatePresence(presence);

        thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    rpc.Discord_RunCallbacks();

                    updatePresenceFields();

                    rpc.Discord_UpdatePresence(presence);

                    Thread.sleep(2000L);
                }
            } catch (InterruptedException ignored) {
            }
        }, "TH-RPC-Handler");
        thread.setDaemon(true);
        thread.start();
    }

    private void updatePresenceFields() {
        presence.details = "User: " + Manager.USER_PROFILE.getName();
        presence.state = "Role: " + Manager.USER_PROFILE.getRole();

        presence.button_label_1 = "Скачать";
        presence.button_url_1 = "https://github.com/Govnokoder123/CheeseVisuals/";
        presence.button_label_2 = "С вопросами";
        presence.button_url_2 = "@thecrasher_321 (дискорд)";

        presence.largeImageKey = "https://raw.githubusercontent.com/Govnokoder123/CheeseVisuals/refs/heads/main/cheese_rpc_icon.gif";
    }

    @Override
    public void onDisable() {
        started = false;
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }
}
