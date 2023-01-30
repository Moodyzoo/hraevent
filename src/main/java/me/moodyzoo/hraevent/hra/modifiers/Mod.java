package me.moodyzoo.hraevent.hra.modifiers;

public interface Mod {

    void enable();

    void disable();

    static String getName() {
        return null;
    }

}
