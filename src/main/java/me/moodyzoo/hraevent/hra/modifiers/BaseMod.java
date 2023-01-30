package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class BaseMod implements Listener, Mod {

    public String name;

    public boolean isHond(Player player) {
        if (player.getName().contains("henRYANand")) {
            return true;
        }
        return false;
    }

    public void enable() {
        Hra.enabledMods.put(this.getClass().getSimpleName(), this);
        Bukkit.getPluginManager().registerEvents(this, Hra.plugin);
    }

    public void disable() {
        Hra.enabledMods.remove(this.getClass().getSimpleName(), this);
        HandlerList.unregisterAll(this);
    }

}
