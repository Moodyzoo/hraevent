package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

@RegisterMod
public class KillChance extends BaseMod {

    public static String getName() {
        return "killkans";
    }

    public void enable() {
        super.enable();
        // enable dingen
    }

    @Override
    public void disable() {
        super.disable();
        // disable dingen
    }

    @EventHandler
    public void onPlayerAttacked(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType() == EntityType.PLAYER) {
            Player player = e.getDamager().getType() == EntityType.PLAYER ? (Player) e.getDamager() : null;
            Player target = e.getEntity().getType() == EntityType.PLAYER ? (Player) e.getEntity() : null;
            if(PlayerManager.isInEvent(player) && PlayerManager.isInEvent(target)) {
                //if player not has permission hra.slaan
                /*if(!player.hasPermission("hra.slaan")) {
                    return;
                }*/

                //get player who hit

                //get player who got hit


                //kill them both

                //50% chance to kill the player who hit
                if(Math.random() > 0.5) {
                    player.setHealth(0);
                }
                target.setHealth(0);
            }

            /*
            //if player has permsission hra.cool
            if(player.hasPermission("hra.cool")) {
                //get player who got hit
                Player target = e.getEntity().getType() == EntityType.PLAYER ? (Player) e.getEntity() : null;
                //check if they are holding a item containing the word "koter"
                if(player.getInventory().getItemInMainHand().getType() == Material.RED_CANDLE) {
                    //kill them
                    target.setHealth(0);
                }
            }

             */
        }
    }

}
