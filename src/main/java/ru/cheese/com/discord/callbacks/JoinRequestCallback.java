package ru.cheese.com.discord.callbacks;

import com.sun.jna.Callback;
import ru.cheese.com.discord.DiscordUser;


public interface JoinRequestCallback extends Callback {
    void apply(final DiscordUser p0);
}
