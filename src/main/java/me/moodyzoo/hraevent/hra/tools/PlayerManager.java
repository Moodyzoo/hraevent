package me.moodyzoo.hraevent.hra.tools;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static List<Player> alive = new ArrayList<Player>();

    public static void backToLobby() {
        World world = Bukkit.getWorld("template1");
        Location loc = world.getSpawnLocation();
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(loc));

    }

    public static void addPlayers() {
        //add players with the permisson itt.player
        //make sure to clear the list first
        alive.clear();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(player.hasPermission("hra.speler")) {
                alive.add(player);
            }
        });
    }

    public static void removePlayer(Player player) {
        alive.remove(player);
    }

    public static void showTitle(String title, ChatColor color) {
        Title titleObject = Title.title(Component.text(color + title), Component.empty(), Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(5), Duration.ofSeconds(0)));
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.showTitle(titleObject);
        });
    }

    public static boolean isInEvent(Player player) {
        return alive.contains(player);
    }

    public static List<Player> getAlive() {
        return alive;
    }
}
