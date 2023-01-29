package me.moodyzoo.hraevent.hra.tools;


import org.bukkit.Location;

public class EventManager {

    private static Location eventLocation;

    public static void setEventLocation(Location location) {
        eventLocation = location;
    }

    public static Location getEventLocation() {
        return eventLocation;
    }

    public static void teleportToEventLocation() {
        PlayerManager.getAlive().forEach(player -> {
            player.teleport(eventLocation);
        });
    }

}
