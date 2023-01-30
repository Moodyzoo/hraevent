package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

@Mod
public class ChickenBomb extends BaseMod {

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
    public void onEggLand(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.EGG) {
            Egg egg = (Egg) event.getEntity();
            Location loc = egg.getLocation();
            Chicken chicken = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
            chicken.setMetadata("noMobLoot", new FixedMetadataValue(Hra.plugin, true));
            //run after 5 seconds
            Bukkit.getScheduler().scheduleSyncDelayedTask(Hra.plugin, new Runnable() {
                @Override
                public void run() {
                    chicken.getWorld().createExplosion(chicken.getLocation(), 2F);
                    chicken.remove();
                }
            }, 50L);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if(event.getEntity() instanceof Chicken && entity.hasMetadata("noMobLoot")) {
            event.getDrops().clear();
        }
    }

}
