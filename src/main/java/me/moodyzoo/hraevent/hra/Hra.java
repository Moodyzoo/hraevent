package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.modifiers.BaseMod;
import me.moodyzoo.hraevent.hra.modifiers.ModAnnotation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Hra extends JavaPlugin {


    public static boolean inGame = false;

    public static HashMap<String, Class<? extends BaseMod>> mods = new HashMap<>();
    public static HashMap<String, BaseMod> enabledMods = new HashMap<>();

    public static Hra plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        //register events
        getServer().getPluginManager().registerEvents(new Events(), this);
        //register commands
        getCommand("hra").setExecutor(new Commands());

        registerMods();

    }

    public Set<Class> findAllModClasses() {
        String packageName = "me.moodyzoo.hraevent.hra.modifiers";
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }


    private void registerMods() {
        Set<Class> modClasses = findAllModClasses();
        for (Class clazz : modClasses) {
            Bukkit.getConsoleSender().sendMessage(clazz.getSimpleName());
            ModAnnotation classAnnotation = (ModAnnotation) clazz.getAnnotation(ModAnnotation.class);
            if (classAnnotation != null) {
                Hra.mods.put(clazz.getSimpleName(), clazz);
            }
        }
    }


    //command


}
