package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RegisterMod
public class DeadlyWater extends BaseMod {

    public static String getName() {
        return "deadlywater";
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

    List<Player> players = new ArrayList<Player>();

    //check voor water
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getBlock().getType() == Material.WATER) {
            players.add(player);
            player.setHealth(0);
        }
    }

    //geef een epic custom death message
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (players.contains(player)) {
                event.deathMessage(Component.text(player.getName()+" ging dood in het water"));
                players.remove(player);
            }
        }
    }

}
