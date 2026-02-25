package ru.cheese.manager;

import ru.cheese.manager.accountManager.AccountManager;
import ru.cheese.manager.commandManager.CommandManager;
import ru.cheese.manager.configManager.ConfigManager;
import ru.cheese.manager.dragManager.DragManager;
import ru.cheese.manager.friendManager.FriendManager;
import ru.cheese.manager.ircManager.IrcManager;
import ru.cheese.manager.macroManager.MacroManager;
import ru.cheese.manager.modulesManager.ChestStealerManager;
import ru.cheese.manager.notificationManager.NotificationManager;
import ru.cheese.manager.proxyManager.ProxyManager;
import ru.cheese.manager.staffManager.StaffManager;
import ru.cheese.manager.themeManager.StyleManager;
import ru.cheese.modules.FunctionManager;
import ru.cheese.modules.combat.rotation.RotationController;
import ru.cheese.protect.UserProfile;
import ru.cheese.manager.fontManager.FontUtils;

public class Manager {
    public static final RotationController ROTATION = RotationController.get();
    public static UserProfile USER_PROFILE;
    public static FunctionManager FUNCTION_MANAGER;
    public static StyleManager STYLE_MANAGER;
    public static NotificationManager NOTIFICATION_MANAGER;
    public static FriendManager FRIEND_MANAGER;
    public static ConfigManager CONFIG_MANAGER;
    public static MacroManager MACROS_MANAGER;
    public static StaffManager STAFF_MANAGER;
    public static CommandManager COMMAND_MANAGER;
    public static DragManager DRAG_MANAGER;
    public static SyncManager SYNC_MANAGER;
    public static FontUtils FONT_MANAGER;
    public static AccountManager ACCOUNT_MANAGER;
    public static ChestStealerManager CHESTSTEALER_MANAGER;
    public static IrcManager IRC_MANAGER;
    public static ProxyManager PROXY_MANAGER;
}
