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
    public void onDie(PlayerDeathEvent e) {
        if(PlayerManager.getAlive().contains(e.getPlayer())) {
            PlayerManager.removePlayer(e.getPlayer());
        }
    }


}
