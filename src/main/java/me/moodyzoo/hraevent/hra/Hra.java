package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.modifiers.BaseMod;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Hra extends JavaPlugin {


    public static boolean inGame = false;

    public static HashMap<String, BaseMod> mods = new HashMap<>();

    public static Hra plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        //register events
        getServer().getPluginManager().registerEvents(new Events(), this);
        //register commands
        getCommand("hra").setExecutor(new Commands());

    }


    //command


}
