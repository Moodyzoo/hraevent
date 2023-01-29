package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class BaseMod implements Listener {

    public BaseMod() {
        Hra.mods.put(this.getClass().getSimpleName(), this);
    }

    public boolean isHond(Player player) {
        if(player.getName().contains("henRYANand")) {
            return true;
        }
        return false;
    }

}
