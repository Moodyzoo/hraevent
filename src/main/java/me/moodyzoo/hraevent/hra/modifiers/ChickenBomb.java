package me.moodyzoo.hraevent.hra.modifiers;

import me.moodyzoo.hraevent.hra.Hra;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ChickenBomb extends BaseMod {

    public ChickenBomb() {
        super();

    }

    @EventHandler
    public void onEggLand(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.EGG) {
            Egg egg = (Egg) event.getEntity();
            Location loc = egg.getLocation();
            Chicken chicken = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
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

}
