package ru.cheese.protect;


import ru.cheese.manager.Manager;

public class NativeHelper {
    public static void setProfile() {
        Manager.USER_PROFILE = new UserProfile(
                "MrCrasher",
                "Admin",
                "Никогда"
        );
    }
}
