package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.modifiers.BaseMod;
import me.moodyzoo.hraevent.hra.modifiers.RegisterMod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
            RegisterMod classAnnotation = (RegisterMod) clazz.getAnnotation(RegisterMod.class);
            if (classAnnotation != null) {
                try {
                    Method nameMethod = clazz.getDeclaredMethod("getName");
                    String name = (String) nameMethod.invoke(null);
                    if (name == null) {
                        throw new RuntimeException(clazz.getSimpleName() + " doesn't override a getName() function.");
                    }
                    Hra.mods.put(name, clazz);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(clazz.getSimpleName() + " doesn't override a getName() function.");
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    //command


}
