package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;


public class Events implements Listener {


    @EventHandler
    public void onPlayerAttacked(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType() == EntityType.PLAYER) {
            Player player = e.getDamager().getType() == EntityType.PLAYER ? (Player) e.getDamager() : null;
            if(Hra.inGame) {
                //if player not has permission hra.slaan
                if(!player.hasPermission("hra.slaan")) {
                    return;
                }

                //get player who hit

                //get player who got hit
                Player target = e.getEntity().getType() == EntityType.PLAYER ? (Player) e.getEntity() : null;

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

    @EventHandler
    public void onDie(PlayerDeathEvent e) {
        if(PlayerManager.getAlive().contains(e.getPlayer())) {
            PlayerManager.removePlayer(e.getPlayer());
        }
    }


}
