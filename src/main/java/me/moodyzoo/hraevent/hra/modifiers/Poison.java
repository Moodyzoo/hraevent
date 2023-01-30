package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RegisterMod
public class Poison extends BaseMod {

    public static String getName() {
        return "gif";
    }

    public void enable() {
        super.enable();
        // enable dingen
        //for every alive player
        PlayerManager.getAlive().forEach(player -> {
            //apply poison effect
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000000, 1));
        });
    }

    @Override
    public void disable() {
        super.disable();
        // disable dingen
        //for every alive player
        PlayerManager.getAlive().forEach(player -> {
            //remove poison effect
            player.removePotionEffect(PotionEffectType.POISON);
        });
    }

    @EventHandler
    //if player quits the game remove poison effect
    public void onQuit(PlayerQuitEvent event) {
        event.getPlayer().removePotionEffect(PotionEffectType.POISON);
    }

}
