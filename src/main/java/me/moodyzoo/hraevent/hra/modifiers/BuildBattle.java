package me.moodyzoo.hraevent.hra.modifiers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;


@RegisterMod
public class BuildBattle extends BaseMod {

    public static String getName() {
        return "buildbattle";
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
    //disable chorus fruit eating
    public void onEat(org.bukkit.event.player.PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.CHORUS_FRUIT) {
            event.setCancelled(true);
        }
    }

    //disable enderpearl throwing
    @EventHandler
    public void onThrow(org.bukkit.event.player.PlayerTeleportEvent event) {
        if(event.getCause() == org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
        }
    }

    //disable ender eye throwing
    @EventHandler
    public void onThrow(org.bukkit.event.player.PlayerInteractEvent event) {
        if(event.getItem().getType() == Material.ENDER_EYE) {
            event.setCancelled(true);
        }
    }

    //disable ender chest opening
    @EventHandler
    public void onOpen(org.bukkit.event.inventory.InventoryOpenEvent event) {
        if(event.getInventory().getType() == org.bukkit.event.inventory.InventoryType.ENDER_CHEST) {
            event.setCancelled(true);
        }
    }





    //disable elytra equip
    @EventHandler
    public void onEquip(org.bukkit.event.player.PlayerToggleFlightEvent event) {
        if(event.getPlayer().getInventory().getChestplate().getType() == Material.ELYTRA) {
            event.setCancelled(true);
        }
    }

    //disable firework launching

    //werkt niet ofzo
    @EventHandler
    public void onFirework(org.bukkit.event.player.PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("hra.admin")) {
            return;
        }
        if(event.getItem().getType() == Material.FIREWORK_ROCKET) {
            event.setCancelled(true);
        }
        if(event.getItem().getType() == Material.TRIDENT) {
            event.setCancelled(true);
        }
    }

    //disable wither
    @EventHandler
    public void onWither(org.bukkit.event.entity.CreatureSpawnEvent event) {
        if(event.getEntity().getType() == org.bukkit.entity.EntityType.WITHER) {
            event.setCancelled(true);
        }
    }



}
