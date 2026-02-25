package ru.cheese.protect;


import ru.cheese.manager.Manager;

public class NativeHelper {
    public static void setProfile() {
        Manager.USER_PROFILE = new UserProfile(
                "levin1337",
                "Deleoper",
                "09.11.2025"
        );
    }
}
