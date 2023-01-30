package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RegisterMod
public class Strength extends BaseMod {

    public static String getName() {
        return "sterk";
    }

    public void enable() {
        super.enable();
        // enable dingen
        //for every alive player
        PlayerManager.getAlive().forEach(player -> {
            //apply strenth effect
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1));
        });
    }

    @Override
    public void disable() {
        super.disable();
        // disable dingen
        //for every alive player
        PlayerManager.getAlive().forEach(player -> {
            //remove poison effect
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        });
    }

    @EventHandler
    //if player quits the game remove effect
    public void onQuit(PlayerQuitEvent event) {
        event.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }

}
